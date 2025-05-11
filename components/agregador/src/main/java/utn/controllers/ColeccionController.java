package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.ColeccionRepository;
import utn.services.ColeccionService;


import java.util.List;

@RestController
@RequestMapping("/colecciones")
public class ColeccionController {

    private final ColeccionService coleccionService;

    @Autowired
    public ColeccionController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }


    @GetMapping
    public List<ColeccionDTO> obtenerColecciones() {
        //TODO Pasar a dto
        return coleccionService.obtenerColecciones();
    }

    @GetMapping("/{identificador}/hechos")
    public List<HechoDTO> obtenerHechosPorColeccion(@PathVariable String identificador) {
        List<Hecho> hechos = coleccionService.obtenerHechosPorColeccion(identificador);
        return hechos.stream()
                .map(HechoMapper::aDTO)
                .toList();
    }


}
