package utn.repositories;

import utn.model.dto.HechoDTO;

import java.util.List;
import java.util.Optional;

public interface IHechoRepository {
    Optional<HechoDTO> findByTitulo(String titulo);
    void save(HechoDTO hecho);
    List<HechoDTO> findAll();
}
