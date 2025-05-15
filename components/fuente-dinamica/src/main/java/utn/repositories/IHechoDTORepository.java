package utn.repositories;

import utn.model.HechoDTO;

import java.util.List;
import java.util.Optional;

public interface IHechoDTORepository {
    Long save(HechoDTO hecho);
    Optional<HechoDTO> findById(Long id);
    List<HechoDTO> findAll();
    void delete(Long id);
    void clear();
}