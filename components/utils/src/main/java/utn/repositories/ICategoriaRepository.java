package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.model.domain.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {
}
