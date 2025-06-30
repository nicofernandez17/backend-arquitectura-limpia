package utn.repositories;

import org.springframework.stereotype.Repository;
import utn.models.domain.Hecho;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HechoRepository {

    private final Map<String, Hecho> hechos = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Guarda un hecho individual (nuevo o actualizado)
    public String save(Hecho hecho) {
        String id = hecho.getId();
        boolean esNuevo = (id == null || id.isBlank());

        if (esNuevo) {
            id = String.valueOf(idGenerator.getAndIncrement());
            hecho.setId(id);
        }

        hechos.put(id, hecho);
        return id;
    }

    // Guarda múltiples hechos
    public List<String> saveAll(List<Hecho> hechosLista) {
        List<String> ids = new ArrayList<>();
        for (Hecho hecho : hechosLista) {
            ids.add(save(hecho));
        }
        System.out.println(ids.size());
        System.out.println(hechos.size());
        return ids;
    }

    // Buscar por ID
    public Optional<Hecho> findById(String id) {
        return Optional.ofNullable(hechos.get(id));
    }

    // Retorna todos los hechos
    public List<Hecho> findAll() {
        return new ArrayList<>(hechos.values());
    }

    // Elimina un hecho por ID
    public void delete(String id) {
        hechos.remove(id);
    }

    // Limpia todos los hechos (por ejemplo para pruebas)
    public void clear() {
        hechos.clear();
        idGenerator.set(1);
    }

    public Optional<Hecho> findIgual(Hecho hecho) {
        return hechos.values().stream()
                .filter(h ->
                        Objects.equals(h.getTitulo(), hecho.getTitulo()) &&
                                Objects.equals(h.getDescripcion(), hecho.getDescripcion()) &&
                                Objects.equals(h.getCategoria(), hecho.getCategoria()) &&
                                Objects.equals(h.getUbicacion(), hecho.getUbicacion()) &&
                                Objects.equals(h.getFecha(), hecho.getFecha())
                )
                .findFirst();
    }

}