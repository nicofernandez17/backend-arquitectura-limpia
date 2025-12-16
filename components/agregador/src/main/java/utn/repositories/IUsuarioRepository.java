package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.SolicitudEliminacion;
import utn.models.domain.Usuario;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByNombreDeUsuario(String username);
    boolean existsByNombreDeUsuario(String nombreDeUsuario);
}
