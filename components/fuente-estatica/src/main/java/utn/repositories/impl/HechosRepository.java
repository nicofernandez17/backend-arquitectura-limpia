package utn.repositories.impl;

import org.springframework.stereotype.Repository;
import utn.model.dto.HechoDTO;
import utn.repositories.IHechoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class HechosRepository implements IHechoRepository {

    private final List<HechoDTO> hechos = new ArrayList<>();

    public Optional<HechoDTO> findByTitulo(String titulo) {
        return hechos.stream()
                .filter(h -> h.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public void save(HechoDTO hecho) {
        // Reemplaza si ya existe con el mismo título
        findByTitulo(hecho.getTitulo()).ifPresentOrElse(
                existente -> {
                    hechos.remove(existente);
                    hechos.add(hecho);
                },
                () -> hechos.add(hecho)
        );
    }


    public List<HechoDTO> findAll() {
        return new ArrayList<>(hechos);
    }
}
