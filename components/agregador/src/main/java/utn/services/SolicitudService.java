package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.domain.SolicitudEliminacion;
import utn.repositories.SolicitudRepository;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }


    public SolicitudEliminacion crearSolicitud(SolicitudEliminacion solicitud) {
        solicitudRepository.save(solicitud);
        return solicitud;
    }
}
