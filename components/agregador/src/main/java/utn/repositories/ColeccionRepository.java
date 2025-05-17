package utn.repositories;


import org.springframework.stereotype.Repository;
import utn.models.domain.Coleccion;
import utn.models.dtos.ColeccionDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ColeccionRepository {

    private final Map<String, Coleccion> colecciones = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public String save(Coleccion coleccion) {
        String id = coleccion.getId();
        boolean isNew = (id == null);

        if (isNew) {
            id = String.valueOf(idGenerator.getAndIncrement());
            coleccion.setId(id);
        } else {
            // Si no existe en el mapa, la inserto igual (nuevo id personalizado)
            // Para evitar que retorne null y no guarde nada
            // Así se inserta o actualiza
        }

        colecciones.put(id, coleccion);

        return id;
    }

    public Optional<Coleccion> findById(String id) {
        return Optional.ofNullable(colecciones.get(id));
    }

    public List<Coleccion> findAll() {
        return new ArrayList<>(colecciones.values());
    }

    public void delete(String id) {
        colecciones.remove(id);
    }

    public void clear() {
        colecciones.clear();
        idGenerator.set(1);
    }
}
