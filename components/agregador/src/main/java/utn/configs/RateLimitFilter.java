package utn.configs;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Bucket bucket;

    public RateLimitFilter() {
        // 100 requests por minuto como ejemplo
        Bandwidth limit = Bandwidth.classic(
                100,
                Refill.intervally(100, Duration.ofMinutes(1))
        );

        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (bucket.tryConsume(1)) {
            // OK → continúa la request
            filterChain.doFilter(request, response);
        } else {
            // Superó el límite → 429
            response.setStatus(429);
            response.getWriter().write("Too Many Requests (Rate Limit Exceeded)");
        }
    }
}
