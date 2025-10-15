package utn.controllers.seeding;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import utn.models.algoritmos.impl.AlgoritmoMayoriaSimple;
import utn.models.algoritmos.impl.AlgoritmoMultiplesMenciones;
import utn.models.domain.Coleccion;
import utn.models.domain.Usuario;
import utn.models.domain.usuarios.Permiso;
import utn.models.domain.usuarios.Rol;
import utn.models.helpers.FuenteNombre;
import utn.repositories.IColeccionRepository;
import utn.repositories.IUsuarioRepository;
import utn.services.AgregadorService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColeccionSeeder implements CommandLineRunner {

    private final IColeccionRepository coleccionRepository;
    private final AgregadorService agregadorService;
    private final IUsuarioRepository usuariosRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public ColeccionSeeder(IColeccionRepository coleccionRepository,
                           AgregadorService agregadorService,
                           IUsuarioRepository usuariosRepository) {
        this.coleccionRepository = coleccionRepository;
        this.agregadorService = agregadorService;
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // igual que LoginService
    }

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    public void seed() {
        // Evita duplicar datos si ya existen
        if (coleccionRepository.count() > 0 || usuariosRepository.count() > 0) {
            System.out.println("ℹ️ Datos iniciales ya existen, no se ejecuta seed.");
            return;
        }

        // Colecciones
        Coleccion coleccion1 = Coleccion.builder()
                .titulo("Actualidad Económica")
                .descripcion("Últimos hechos relevantes en economía")
                .fuentes(new ArrayList<>(List.of(FuenteNombre.DINAMICA, FuenteNombre.PROXY)))
                .algoritmo(new AlgoritmoMultiplesMenciones())
                .build();

        Coleccion coleccion2 = Coleccion.builder()
                .titulo("Innovación Tecnológica")
                .descripcion("Novedades y avances tecnológicos")
                .fuentes(new ArrayList<>(List.of(FuenteNombre.ESTATICA)))
                .algoritmo(new AlgoritmoMayoriaSimple())
                .build();

        Coleccion coleccion3 = Coleccion.builder()
                .titulo("Tecnología y Sociedad")
                .descripcion("Impacto de la tecnología en la sociedad actual")
                .fuentes(new ArrayList<>(List.of(FuenteNombre.ESTATICA, FuenteNombre.DINAMICA)))
                .algoritmo(new AlgoritmoMultiplesMenciones())
                .build();

        coleccionRepository.saveAll(List.of(coleccion1, coleccion2, coleccion3));

        // Usuarios
        Usuario admin = new Usuario();
        admin.setNombre("Administrador");
        admin.setNombreDeUsuario("admin@gmail.com");
        admin.setContrasenia(passwordEncoder.encode("admin123"));
        admin.setRol(Rol.ADMINISTRADOR);
        admin.setPermisos(List.of(Permiso.LEER, Permiso.ESCRIBIR, Permiso.ELIMINAR));

        Usuario user = new Usuario();
        user.setNombre("Usuario Normal");
        user.setNombreDeUsuario("user@gmail.com");
        user.setContrasenia(passwordEncoder.encode("user123"));
        user.setRol(Rol.USUARIO);
        user.setPermisos(List.of(Permiso.LEER));

        usuariosRepository.saveAll(List.of(admin, user));

        System.out.println("✅ Datos iniciales (colecciones y usuarios) creados correctamente.");
    }
}
