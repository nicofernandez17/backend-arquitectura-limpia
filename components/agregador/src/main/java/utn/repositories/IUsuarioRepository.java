package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.SolicitudEliminacion;
import utn.models.domain.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
}
