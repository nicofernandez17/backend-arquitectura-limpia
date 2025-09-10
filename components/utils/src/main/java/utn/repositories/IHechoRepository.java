package utn.repositories;

import utn.models.domain.Hecho;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IHechoRepository extends JpaRepository<Hecho, Long> {

    // Spring Data JPA genera la consulta automáticamente
    Optional<Hecho> findByTitulo(String titulo);


}
