package utn.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utn.models.domain.Usuario;
import utn.models.dtos.UserRolesPermissionsDTO;
import utn.repositories.IUsuarioRepository;
import utn.utils.JwtUtil;

import java.util.Optional;

@Service
public class LoginService {
    
    private final IUsuarioRepository usuariosRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public LoginService(IUsuarioRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    
    public Usuario autenticarUsuario(String username, String password) {
        Optional<Usuario> usuarioOpt = usuariosRepository.findByNombreDeUsuario(username);
        
        if (usuarioOpt.isEmpty()) {
            throw new NotFoundException("Usuario", username);
        }
        
        Usuario usuario = usuarioOpt.get();
        
        // Verificar la contraseña usando BCrypt
        if (!passwordEncoder.matches(password, usuario.getContrasenia())) {
            throw new NotFoundException("Usuario", username);
        }
        
        return usuario;
    }
    
    public String generarAccessToken(String username) {
        return JwtUtil.generarAccessToken(username);
    }
    
    public String generarRefreshToken(String username) {
        return JwtUtil.generarRefreshToken(username);
    }
    
    public UserRolesPermissionsDTO obtenerRolesYPermisosUsuario(String username) {
        Optional<Usuario> usuarioOpt = usuariosRepository.findByNombreDeUsuario(username);
        
        if (usuarioOpt.isEmpty()) {
            throw new NotFoundException("Usuario", username);
        }
        
        Usuario usuario = usuarioOpt.get();
        
        return UserRolesPermissionsDTO.builder()
                .username(usuario.getNombreDeUsuario())
                .rol(usuario.getRol())
                .permisos(usuario.getPermisos())
                .build();
    }
}