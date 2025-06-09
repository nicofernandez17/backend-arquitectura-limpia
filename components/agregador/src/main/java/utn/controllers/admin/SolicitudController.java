package utn.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.domain.SolicitudEliminacion;
import utn.services.SolicitudService;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<SolicitudEliminacion> procesarSolicitud(
            @PathVariable Long id,
            @RequestBody String accion
    ) {
        SolicitudEliminacion solicitud = solicitudService.procesarSolicitud(id, accion);
        return ResponseEntity.ok(solicitud);
    }
}
