package utn.repositories;

import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;

import java.util.List;
import java.util.Optional;

public interface IHechoRepository {
    Optional<Hecho> findByTitulo(String titulo);
    void save(Hecho hecho);
    List<Hecho> findAll();
}
