package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.models.helpers.Categoria;

import java.util.Optional;

public interface ICategoriaRepository extends JpaRepository<Categoria,Integer> {
    public Optional<Categoria> findByNombre(String nombre);
}
