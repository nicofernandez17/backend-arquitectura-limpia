package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.estadisticas.Estadistica;

public interface IEstadisticaRepository extends JpaRepository<Estadistica, Long> {
}
