package utn.services;

import org.springframework.stereotype.Service;
import utn.model.domain.Revision;
import utn.model.dtos.HechoDTO;
import utn.model.dtos.RevisionProcesarDTO;
import utn.model.helpers.EstadoRevision;
import utn.repositories.RevisionRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RevisionService {

    private final HechoService hechoService;
    private final RevisionRepository revisionRepository;

    public RevisionService(HechoService hechoService, RevisionRepository revisionRepository) {
        this.hechoService = hechoService;
        this.revisionRepository = revisionRepository;
    }

    public Revision crearRevision(HechoDTO hechoDTO,String id) {

        return revisionRepository.save(new Revision(hechoDTO,id));
    }

    public void procesarRevision(Long revisionId, RevisionProcesarDTO request) {
        if (request.isAceptado()) {
            aceptarRevision(revisionId);
        } else {
            rechazarRevision(revisionId, request.getComentario());
        }
    }

    public void aceptarRevision(Long revisionId) {
        Revision revision = revisionRepository.findById(revisionId);

        HechoDTO dtoPropuesto = revision.getContenidoPropuesto();

        // Aplica los cambios usando el HechoService
        hechoService.registrarHecho(dtoPropuesto);

        // Actualiza el estado de la revisión
        revision.setEstado(EstadoRevision.ACEPTADA);
        revision.setFechaRevision(LocalDateTime.now());

        revisionRepository.save(revision);
    }

    public void rechazarRevision(Long revisionId, String comentarioModerador) {
        Revision revision = revisionRepository.findById(revisionId);

        revision.setEstado(EstadoRevision.RECHAZADA);
        revision.setComentarioModerador(comentarioModerador);
        revision.setFechaRevision(LocalDateTime.now());

        revisionRepository.save(revision);
    }
}