package controllers;

import models.dtos.output.HechoOutputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.IHechosService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechosController {

    private final IHechosService hechoService;

    public HechosController(IHechosService hechoService) {
        this.hechoService = hechoService;
    }

    @GetMapping
    public ResponseEntity<List<HechoOutputDTO>> obtenerHechos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) LocalDate fecha_reporte_desde,
            @RequestParam(required = false) LocalDate fecha_reporte_hasta,
            @RequestParam(required = false) LocalDate fecha_acontecimiento_desde,
            @RequestParam(required = false) LocalDate fecha_acontecimiento_hasta,
            @RequestParam(required = false) String ubicacion
    ) {
        List<HechoOutputDTO> hechos = hechoService.filtrarHechos(categoria, fecha_reporte_desde, fecha_reporte_hasta,
                fecha_acontecimiento_desde, fecha_acontecimiento_hasta, ubicacion);
        return ResponseEntity.ok(hechos);
    }
}
