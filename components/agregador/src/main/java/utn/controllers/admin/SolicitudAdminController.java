package utn.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.domain.SolicitudEliminacion;
import utn.models.dtos.SolicitudDTO;
import utn.models.dtos.SolicitudMapper;
import utn.services.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/solicitudes")
public class SolicitudAdminController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudAdminController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> obtenerTodas(){
        List<SolicitudDTO> solicitudes = solicitudService.obtenerSolicitudes().stream().map(SolicitudMapper::toDTO).toList();
        return ResponseEntity.ok(solicitudes);
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
