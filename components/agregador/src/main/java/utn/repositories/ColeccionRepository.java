package utn.repositories;


import org.springframework.stereotype.Repository;
import utn.models.Coleccion;

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
        // Lógica para actualizar una colección, puede ser por nombre o id
    }
}
