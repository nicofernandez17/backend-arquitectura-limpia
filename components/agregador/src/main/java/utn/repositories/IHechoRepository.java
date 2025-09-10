package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.Hecho;

public interface IHechoRepository extends JpaRepository<Hecho, String> {
}
