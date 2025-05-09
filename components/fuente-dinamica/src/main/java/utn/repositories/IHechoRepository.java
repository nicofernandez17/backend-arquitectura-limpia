package utn.repositories;

import utn.model.HechoDTO;

import java.util.List;
import java.util.Optional;

public interface IHechoRepository {
    Optional<HechoDTO> findByTitulo(String titulo);
    void save(HechoDTO hecho);
    List<HechoDTO> findAll();
    Optional<HechoDTO> findById(Long id);
}
