package utn.controllers.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.domain.SolicitudEliminacion;
import utn.models.dtos.SolicitudDTO;
import utn.services.SolicitudService;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<SolicitudEliminacion> crearSolicitud(@RequestBody SolicitudDTO solicitud) {
        SolicitudEliminacion nuevaSolicitud = solicitudService.crearSolicitud(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }
}
