package utn.repositories;


import org.springframework.stereotype.Repository;
import utn.models.Coleccion;
import utn.models.ColeccionDTO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ColeccionRepository {
    private final List<ColeccionDTO> colecciones = new ArrayList<>();

    public List<ColeccionDTO> getAll() {
        return colecciones;
    }

    public void add(ColeccionDTO coleccion) {
        colecciones.add(coleccion);
    }

    public void update(ColeccionDTO coleccion) {
        //TODO
    }
}
