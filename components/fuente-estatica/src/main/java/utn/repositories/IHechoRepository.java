package utn.repositories;

import utn.model.domain.Hecho;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface IHechoRepository extends JpaRepository<Hecho, Long> {

    // Spring Data JPA genera la consulta automáticamente
    Optional<Hecho> findByTitulo(String titulo);


}
