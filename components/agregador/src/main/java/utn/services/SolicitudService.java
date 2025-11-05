package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;
import utn.models.dtos.SolicitudDTO;
import utn.models.dtos.SolicitudMapper;
import utn.models.helpers.EstadoSolicitud;
import utn.repositories.IHechoRepository;
import utn.repositories.ISolicitudRepository;
import utn.services.spamDetector.akismet.AkismetService;
import utn.services.spamDetector.ISpamDetector;
import utn.repositories.SolicitudRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final ISolicitudRepository solicitudRepository;
    private final ISpamDetector spamDetector;
    private final IHechoRepository hechoRepository;



    @Autowired
    public SolicitudService(ISolicitudRepository solicitudRepository, ISpamDetector spamDetector, IHechoRepository hechoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.spamDetector = spamDetector;
        this.hechoRepository = hechoRepository;
    }

    public List<SolicitudEliminacion> obtenerSolicitudes(){
        return solicitudRepository.findAll();
    }

    public SolicitudEliminacion crearSolicitud(SolicitudDTO solicitudDTO) {

        // Buscar el hecho asociado
        Hecho hecho = hechoRepository.findById(Long.valueOf(solicitudDTO.getHecho()))
                .orElseThrow(() -> new IllegalArgumentException("Hecho no encontrado con ID: " + solicitudDTO.getHecho()));

        // Convertir DTO → entidad de dominio
        SolicitudEliminacion solicitud = SolicitudMapper.toDomain(solicitudDTO, hecho);

        // Determinar estado según detección de spam
        if (spamDetector.esSpam(solicitudDTO.getMotivo())) {
            solicitud.setEstado(EstadoSolicitud.RECHAZADA);
        } else {
            solicitud.setEstado(EstadoSolicitud.PENDIENTE);
        }

        // Guardar en base de datos
        return solicitudRepository.save(solicitud);
    }

    public SolicitudEliminacion procesarSolicitud(Long id, String accion) {

        SolicitudEliminacion solicitud = solicitudRepository.findById(id).orElse(null);

        if (accion == "aceptar"){
            solicitud.aceptar();
        } else if (accion == "rechazar"){solicitud.rechazar();}


        return solicitud;

    }


}
