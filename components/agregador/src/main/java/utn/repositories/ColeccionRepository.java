package utn.repositories;


import org.springframework.stereotype.Repository;
import utn.models.domain.Coleccion;
import utn.models.dtos.ColeccionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ColeccionRepository {
    private final List<Coleccion> colecciones = new ArrayList<>();

    public List<Coleccion> getAll() {
        return colecciones;
    }

    public void add(Coleccion coleccion) {
        colecciones.add(coleccion);
    }

    public Optional<Coleccion> findById(String id) {
        return colecciones.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    public void update(Coleccion coleccion) {
        //TODO
    }
}
