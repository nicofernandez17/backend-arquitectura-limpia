package utn.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SolicitudRepository {

    private final List<SolicitudEliminacion> solicitudes = new ArrayList<>();

    public void save(SolicitudEliminacion solicitud) {
        solicitudes.add(solicitud);
    }

    public List<SolicitudEliminacion> findAll() {
        return new ArrayList<>(solicitudes); // Defensive copy
    }

    public Optional<SolicitudEliminacion> findByFact(Hecho hecho) {
        return solicitudes.stream()
                .filter(s -> s.getHecho().equals(hecho))
                .findFirst();
    }

    public void delete(SolicitudEliminacion solicitud) {
        solicitudes.remove(solicitud);
    }
}