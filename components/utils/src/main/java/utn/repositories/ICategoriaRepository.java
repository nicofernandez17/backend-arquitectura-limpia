package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.domain.Categoria;

import java.util.Optional;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombre(String nombre);
}
