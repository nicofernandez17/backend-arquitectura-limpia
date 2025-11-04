package utn.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import utn.utils.JwtUtil;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String username = JwtUtil.validarToken(token);
                var auth = new UsernamePasswordAuthenticationToken(
                    username, 
                    null, 
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            }
        } else {
            System.out.println("No hay token de autorización");
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        // No aplicar el filtro JWT a los endpoints públicos
        return path.equals("/api/auth") ||
                path.equals("/api/auth/refresh") ||
                path.startsWith("/api/admin/hechos") ||
                path.startsWith("/api/hechos") ||
                path.startsWith("/api/admin/colecciones") ||
                path.equals("/colecciones/inicializar") ||
                path.startsWith("/api/colecciones");
    }
}