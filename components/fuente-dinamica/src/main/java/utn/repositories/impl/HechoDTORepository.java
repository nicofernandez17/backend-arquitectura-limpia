package utn.repositories.impl;

import org.springframework.stereotype.Repository;
import utn.model.HechoDTO;
import utn.repositories.IHechoDTORepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HechoDTORepository implements IHechoDTORepository {
    private final Map<Long, HechoDTO> hechos = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Guarda un nuevo hecho, asignando un ID internamente
    @Override
    public Long save(HechoDTO hecho) {
        Long id = idGenerator.getAndIncrement();
        HechoDTO hechoConId = HechoDTO.builder()
                .id(id)
                .titulo(hecho.getTitulo())
                .descripcion(hecho.getDescripcion())
                .categoria(hecho.getCategoria())
                .latitud(hecho.getLatitud())
                .longitud(hecho.getLongitud())
                .fecha_hecho(hecho.getFecha_hecho())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        hechos.put(id, hechoConId);
        return id;
    }

    public Optional<HechoDTO> findById(Long id) {
        return Optional.ofNullable(hechos.get(id));
    }

    public List<HechoDTO> findAll() {
        return new ArrayList<>(hechos.values());
    }

    @Override
    public void update(Long id, HechoDTO actualizado) {
        if (hechos.containsKey(id)) {
            HechoDTO hechoViejo = hechos.get(id);
            HechoDTO nuevo = HechoDTO.builder()
                    .id(id)
                    .titulo(actualizado.getTitulo())
                    .descripcion(actualizado.getDescripcion())
                    .categoria(actualizado.getCategoria())
                    .latitud(actualizado.getLatitud())
                    .longitud(actualizado.getLongitud())
                    .fecha_hecho(actualizado.getFecha_hecho())
                    .created_at(hechoViejo.getCreated_at()) // preserva el created_at
                    .updated_at(LocalDateTime.now())
                    .build();

            hechos.put(id, nuevo);
        }
    }

    public void delete(Long id) {
        hechos.remove(id);
    }

    public void clear() {
        hechos.clear();
        idGenerator.set(1);
    }
}