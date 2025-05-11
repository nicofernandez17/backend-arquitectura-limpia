package utn.repositories;


import org.springframework.stereotype.Repository;
import utn.models.domain.Coleccion;
import utn.models.dtos.ColeccionDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ColeccionRepository {
    private final List<Coleccion> colecciones = new ArrayList<>();

    public List<Coleccion> getAll() {
        return colecciones;
    }

    public void add(Coleccion coleccion) {
        colecciones.add(coleccion);
    }

    public void update(Coleccion coleccion) {
        //TODO
    }
}
