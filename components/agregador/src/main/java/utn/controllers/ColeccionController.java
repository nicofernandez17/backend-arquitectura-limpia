package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.Coleccion;
import utn.repositories.ColeccionRepository;
import utn.services.AgregadorServices;

import java.util.List;

@RestController
@RequestMapping("/colecciones")
public class ColeccionController {

    private final ColeccionRepository coleccionRepository;

    @Autowired
    public ColeccionController(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    @GetMapping
    public List<Coleccion> obtenerColecciones() {
        return coleccionRepository.getAll();
    }
}
