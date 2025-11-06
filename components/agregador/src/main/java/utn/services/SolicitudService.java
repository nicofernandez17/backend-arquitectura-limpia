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
        System.out.println("➡️ Procesando solicitud de eliminación: id=" + id + ", acción='" + accion + "'");

        SolicitudEliminacion solicitud = solicitudRepository.findById(id).orElse(null);
        if (solicitud == null) {
            System.out.println("⚠️ No se encontró ninguna solicitud con id=" + id);
            throw new IllegalArgumentException("Solicitud no encontrada con id=" + id);
        }

        System.out.println("🔍 Estado actual de la solicitud: " + solicitud.getEstado());

        if ("aceptar".equalsIgnoreCase(accion)) {
            solicitud.aceptar();
            System.out.println("✅ Solicitud con id=" + id + " marcada como ACEPTADA");
        } else if ("rechazar".equalsIgnoreCase(accion)) {
            solicitud.rechazar();
            System.out.println("❌ Solicitud con id=" + id + " marcada como RECHAZADA");
        } else {
            System.out.println("🚫 Acción inválida recibida: '" + accion + "'");
            throw new IllegalArgumentException("Acción no válida: " + accion);
        }

        SolicitudEliminacion guardada = solicitudRepository.save(solicitud);
        System.out.println("💾 Solicitud guardada correctamente con id=" + guardada.getId()
                + ", nuevo estado=" + guardada.getEstado());

        if (guardada.getHecho() != null) {
            System.out.println("📘 Hecho asociado -> id=" + guardada.getHecho().getId()
                    + ", título=" + guardada.getHecho().getTitulo());
        }

        return guardada;
    }


}
