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

    public Long save(HechoDTO hecho) {
        Long id = hecho.getId();
        boolean isNew = (id == null);

        if (isNew) {
            id = idGenerator.getAndIncrement();
        } else {
            // Si el id no existe, no se puede actualizar, devolver null
            if (!hechos.containsKey(id)) {
                return null;
            }
        }

        HechoDTO existente = hechos.get(id);
        LocalDateTime createdAt = isNew
                ? LocalDateTime.now()
                : (existente != null ? existente.getCreated_at() : LocalDateTime.now());

        HechoDTO hechoConId = HechoDTO.builder()
                .id(id)
                .titulo(hecho.getTitulo())
                .descripcion(hecho.getDescripcion())
                .categoria(hecho.getCategoria())
                .latitud(hecho.getLatitud())
                .longitud(hecho.getLongitud())
                .fecha_hecho(hecho.getFecha_hecho())
                .created_at(createdAt)
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

    public void delete(Long id) {
        hechos.remove(id);
    }

    public void clear() {
        hechos.clear();
        idGenerator.set(1);
    }
}