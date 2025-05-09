package utn.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.model.HechoDTO;
import utn.services.HechoService;

import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechoAPIController {

    private HechoService hechoService;

    @GetMapping
    public List<HechoDTO> obtenerHechos() {
        return hechoService.obtenerTodos();
    }

}
