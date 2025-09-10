package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import utn.models.domain.Hecho;

import java.util.Optional;

public interface IHechoRepository extends JpaRepository<Hecho, Long> {
    Optional<Hecho> findByClaveLogica(String claveLogica);
    Optional<Hecho> findByClaveHash(String claveHash);

}
