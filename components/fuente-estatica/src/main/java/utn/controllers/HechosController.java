package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.dto.HechoDTO;
import utn.models.dto.HechoMapper;
import utn.services.HechosService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechosController {

    private final HechosService hechosService;

    @Autowired
    public HechosController(HechosService hechosService) {
        this.hechosService = hechosService;
    }


    @PostMapping
    public ResponseEntity<String> cargarDesdeCsv() {
        hechosService.cargarDesdeCsv();
        return ResponseEntity.ok("Se inicio la carga y procesamiento de los hechos.");
    }




    @GetMapping
    public List<HechoDTO> obtenerHechosDesde(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde) {


        return hechosService.obtenerDesdeFecha(desde).stream().map(HechoMapper :: aDTO).toList();
    }

}
