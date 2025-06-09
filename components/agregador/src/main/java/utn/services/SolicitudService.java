package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.domain.SolicitudEliminacion;
import utn.models.helpers.EstadoSolicitud;
import utn.services.spamDetector.akismet.AkismetService;
import utn.services.spamDetector.ISpamDetector;
import utn.repositories.SolicitudRepository;

import java.util.Optional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ISpamDetector spamDetector;



    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository, ISpamDetector spamDetector) {
        this.solicitudRepository = solicitudRepository;
        this.spamDetector = spamDetector;
    }


    public SolicitudEliminacion crearSolicitud(SolicitudEliminacion solicitud) {

        if (spamDetector.esSpam(solicitud.getMotivo())){
            solicitud.setEstado(EstadoSolicitud.RECHAZADA);
        } else {
            solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        }


        solicitudRepository.save(solicitud);
        return solicitud;
    }

    public SolicitudEliminacion procesarSolicitud(Long id, String accion) {

        SolicitudEliminacion solicitud = solicitudRepository.findById(id).orElse(null);

        if (accion == "aceptar"){
            solicitud.aceptar();
        } else if (accion == "rechazar"){solicitud.rechazar();}


        return solicitud;

    }


}
