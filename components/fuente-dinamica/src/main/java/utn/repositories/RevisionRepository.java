package utn.repositories;

import org.springframework.stereotype.Repository;
import utn.model.domain.Revision;
import utn.model.helpers.EstadoRevision;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class RevisionRepository {

    private final Map<Long, Revision> revisiones = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Revision save(Revision revision) {
        if (revision.getId() == null) {
            revision.setId(idGenerator.getAndIncrement());
        }
        revisiones.put(revision.getId(), revision);
        return revision;
    }

    public Revision findById(Long id) {
        return revisiones.get(id);
    }

    public List<Revision> findByEstado(EstadoRevision estado) {
        return revisiones.values().stream()
                .filter(r -> r.getEstado() == estado)
                .collect(Collectors.toList());
    }


    public List<Revision> findAll() {
        return new ArrayList<>(revisiones.values());
    }

    public void deleteById(Long id) {
        revisiones.remove(id);
    }

    public void clear() {
        revisiones.clear();
    }
}
