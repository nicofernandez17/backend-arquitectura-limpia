package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import utn.model.domain.estadisticas.Estadistica;

public interface IEstadisticaRepository extends JpaRepository<Estadistica, Long> {
}
