package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import utn.models.dtos.input.SolicitudInputDTO;
import utn.models.dtos.output.SolicitudOutputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.services.ISolicitudesService;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Autowired
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
