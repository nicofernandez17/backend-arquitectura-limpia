package utn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.services.ColeccionService;

import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechosController {

    private final ColeccionService coleccionService;

    public HechosController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    @GetMapping
    public List<HechoDTO> obtenerTodosLosHechos() {
        return coleccionService.obtenerColecciones().stream()
                .flatMap(coleccion -> coleccion.getHechos().stream())
                .map(HechoMapper::aDTO)
                .toList();
    }
}
