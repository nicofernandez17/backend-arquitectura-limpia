package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.models.dtos.HechoDTO;
import utn.services.HechoService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechoAPIController {

    private final HechoService hechoService;

    @Autowired
    public HechoAPIController(HechoService hechoService) {
        this.hechoService = hechoService;
    }
/*
    @GetMapping
    public List<HechoDTO> obtenerHechos() {
        return hechoService.obtenerTodos()
                .stream()
                .map(HechoMapper::aDTO)
                .toList();
    }
    */
    @GetMapping
    public List<HechoDTO> obtenerHechosDesde(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde) {


        return hechoService.obtenerDesdeFecha(desde);
    }

}
