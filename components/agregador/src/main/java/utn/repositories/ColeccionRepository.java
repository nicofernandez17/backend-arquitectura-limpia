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

    // ========================
    // Crear o actualizar
    // ========================
    public String save(Coleccion coleccion) {
        String id = coleccion.getId();
        boolean isNew = (id == null || id.isBlank());

        if (isNew) {
            id = String.valueOf(idGenerator.getAndIncrement());
            coleccion.setId(id);
        }

        colecciones.put(id, coleccion); // inserta o actualiza
        return id;
    }

    // ========================
    // Guardar múltiples colecciones
    // ========================
    public List<String> saveAll(List<Coleccion> coleccionesNuevas) {
        List<String> ids = new ArrayList<>();
        for (Coleccion coleccion : coleccionesNuevas) {
            String id = save(coleccion);
            ids.add(id);
        }
        return ids;
    }

    // ========================
    // Lectura
    // ========================
    public Optional<Coleccion> findById(String id) {
        return Optional.ofNullable(colecciones.get(id));
    }

    public List<Coleccion> findAll() {
        return new ArrayList<>(colecciones.values());
    }

    public boolean existsById(String id) {
        return colecciones.containsKey(id);
    }

    // ========================
    // Eliminación
    // ========================
    public void delete(String id) {
        colecciones.remove(id);
    }

    public void deleteAll() {
        colecciones.clear();
        idGenerator.set(1); // opcional: reiniciar IDs
    }

    // ========================
    // Utilidad extra
    // ========================
    public long count() {
        return colecciones.size();
    }

    public void update(String id, Coleccion nuevaColeccion) {
        if (colecciones.containsKey(id)) {
            nuevaColeccion.setId(id);
            colecciones.put(id, nuevaColeccion);
        }
    }
}
