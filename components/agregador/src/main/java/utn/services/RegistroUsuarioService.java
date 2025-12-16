package utn.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utn.models.domain.Usuario;
import utn.models.domain.usuarios.Rol;
import utn.models.dtos.RegistroUsuarioDTO;
import utn.repositories.IUsuarioRepository;

import java.util.ArrayList;

@Service
public class RegistroUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistroUsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registrar(RegistroUsuarioDTO dto) {

        if (usuarioRepository.existsByNombreDeUsuario(dto.getNombreDeUsuario())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        Usuario usuario = Usuario.builder()
                .nombre(dto.getNombre())
                .nombreDeUsuario(dto.getNombreDeUsuario())
                .contrasenia(passwordEncoder.encode(dto.getContrasenia()))
                .rol(Rol.USUARIO)              // rol inicial
                .permisos(new ArrayList<>())   // sin permisos al crear
                .build();

        usuarioRepository.save(usuario);
    }
}