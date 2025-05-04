package controllers;

import models.dtos.input.SolicitudInputDTO;
import models.dtos.output.SolicitudOutputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.ISolicitudesService;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudesController {

    private final ISolicitudesService solicitudService;


    public SolicitudesController(ISolicitudesService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<SolicitudOutputDTO> crearSolicitud(@RequestBody  SolicitudInputDTO solicitudDTO) {
        SolicitudOutputDTO resultado = solicitudService.crearSolicitud(solicitudDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

}
