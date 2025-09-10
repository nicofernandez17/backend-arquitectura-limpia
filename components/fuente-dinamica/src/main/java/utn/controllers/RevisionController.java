package utn.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.dtos.RevisionProcesarDTO;
import utn.services.RevisionService;

@RestController
@RequestMapping("/revisiones")
public class RevisionController {

    private final RevisionService revisionService;

    public RevisionController(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    // Actualiza el estado de una revisión (aceptar o rechazar)
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarEstadoRevision(
            @PathVariable Long id,
            @RequestBody RevisionProcesarDTO request) {

        revisionService.procesarRevision(id, request);
        return ResponseEntity.ok().build();
    }
}
