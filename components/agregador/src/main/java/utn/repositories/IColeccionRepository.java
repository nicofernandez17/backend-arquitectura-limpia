package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.Coleccion;

public interface IColeccionRepository extends JpaRepository<Coleccion,Long> {
}
