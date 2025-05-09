package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.model.HechoDTO;
import utn.services.HechosService;

import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechosController {

    private final HechosService hechosService;

    @Autowired
    public HechosController(HechosService hechosService) {
        this.hechosService = hechosService;
    }

    @GetMapping
    public List<HechoDTO> obtenerTodosLosHechos() {
        return hechosService.obtenerHechos();
    }
}
