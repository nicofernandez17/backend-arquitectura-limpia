package utn.repositories;

import org.springframework.stereotype.Repository;
import utn.models.domain.Hecho;
import utn.models.helpers.HechoClaveUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HechoRepository {

    private final Map<String, Hecho> hechosPorClave = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public String save(Hecho hecho) {
        String clave = HechoClaveUtils.generarClaveLogica(hecho);

        Hecho existente = hechosPorClave.get(clave);

        if (existente != null) {
            existente.agregarFuentes(hecho.getFuentes());
            return existente.getId();
        }

        String id = String.valueOf(idGenerator.getAndIncrement());
        hecho.setId(id);
        hechosPorClave.put(clave, hecho);

        return id;
    }

    public List<String> saveAll(List<Hecho> hechosLista) {
        List<String> ids = new ArrayList<>();
        for (Hecho hecho : hechosLista) {
            ids.add(save(hecho));
        }
        System.out.println("Hechos únicos: " + hechosPorClave.size());
        return ids;
    }

    public Optional<Hecho> findById(String id) {
        return hechosPorClave.values().stream()
                .filter(h -> h.getId().equals(id))
                .findFirst();
    }

    public List<Hecho> findAll() {
        return new ArrayList<>(hechosPorClave.values());
    }

    public void delete(String id) {
        hechosPorClave.entrySet().removeIf(entry -> entry.getValue().getId().equals(id));
    }

    public void clear() {
        hechosPorClave.clear();
        idGenerator.set(1);
    }

    public Optional<Hecho> findIgual(Hecho hecho) {
        return Optional.ofNullable(
                hechosPorClave.get(HechoClaveUtils.generarClaveLogica(hecho))
        );
    }
}
