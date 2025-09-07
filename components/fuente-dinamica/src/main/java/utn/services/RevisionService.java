package utn.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import utn.model.domain.Hecho;
import utn.model.domain.Revision;
import utn.model.dtos.HechoDTO;
import utn.model.dtos.HechoMapper;
import utn.model.dtos.RevisionProcesarDTO;
import utn.model.helpers.EstadoRevision;
import utn.repositories.IRevisionRepository;
import utn.repositories.RevisionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RevisionService {

    private final HechoService hechoService;
    private final IRevisionRepository revisionRepository;

    public RevisionService(HechoService hechoService, IRevisionRepository revisionRepository) {
        this.hechoService = hechoService;
        this.revisionRepository = revisionRepository;
    }

    // Crear revisión a partir de un Hecho
    public Revision crearRevision(Hecho hechoPropuesto, String id) {
        return revisionRepository.save(new Revision(hechoPropuesto,id));
    }

    public void procesarRevision(Long revisionId, RevisionProcesarDTO request) {
        if (request.isAceptado()) {
            aceptarRevision(revisionId);
        } else {
            rechazarRevision(revisionId, request.getComentario());
        }
    }

    public void aceptarRevision(Long revisionId) {
        Revision revision = revisionRepository.findById(revisionId)
                .orElseThrow(() -> new EntityNotFoundException("Revision no encontrada con id: " + revisionId));

        Hecho hechoPropuesto = revision.getContenidoPropuesto();

        // Aplica los cambios usando el HechoService
        hechoService.registrarHecho(HechoMapper.aDTO(hechoPropuesto));

        // Actualiza el estado de la revisión
        revision.setEstado(EstadoRevision.ACEPTADA);
        revision.setFechaRevision(LocalDateTime.now());

        revisionRepository.save(revision);
    }

    public void rechazarRevision(Long revisionId, String comentarioModerador) {
        Revision revision = revisionRepository.findById(revisionId)
                .orElseThrow(() -> new EntityNotFoundException("Revision no encontrada con id: " + revisionId));

        revision.setEstado(EstadoRevision.RECHAZADA);
        revision.setComentarioModerador(comentarioModerador);
        revision.setFechaRevision(LocalDateTime.now());

        revisionRepository.save(revision);
    }
}