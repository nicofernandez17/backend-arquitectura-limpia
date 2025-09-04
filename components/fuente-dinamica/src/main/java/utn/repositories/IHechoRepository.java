package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.model.domain.Hecho;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IHechoRepository extends JpaRepository<Hecho, Long> {

    List<Hecho> findByFechaDeCargaAfter(LocalDateTime fecha);

}
