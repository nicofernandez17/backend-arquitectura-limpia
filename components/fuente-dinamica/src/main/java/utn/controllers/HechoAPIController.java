package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.model.dtos.HechoDTO;
import utn.model.dtos.HechoMapper;
import utn.services.HechoService;

import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechoAPIController {

    private final HechoService hechoService;

    @Autowired
    public HechoAPIController(HechoService hechoService) {
        this.hechoService = hechoService;
    }

    @GetMapping
    public List<HechoDTO> obtenerHechos() {
        return hechoService.obtenerTodos()
                .stream()
                .map(HechoMapper::aDTO)
                .toList();
    }

}
