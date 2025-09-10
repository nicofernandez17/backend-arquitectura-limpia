package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.models.domain.Hecho;

@Repository
public interface IHechoRepository extends JpaRepository<Hecho, Long> {

}
