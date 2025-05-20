package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping
    public ResponseEntity<SolicitudEliminacion> crearSolicitud(@RequestBody SolicitudEliminacion solicitud) {
        SolicitudEliminacion nuevaSolicitud = solicitudService.crearSolicitud(solicitud);
        return ResponseEntity.ok(nuevaSolicitud);
    }
}
