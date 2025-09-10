package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.SolicitudEliminacion;

public interface ISolicitudEliminacionRepository extends JpaRepository<SolicitudEliminacion, Integer> {
}
