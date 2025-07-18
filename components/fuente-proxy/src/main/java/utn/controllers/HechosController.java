package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import utn.model.dto.HechoDTO;
import utn.services.ProxyService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class HechosController {

    @Autowired
    private ProxyService proxyService;

    @PostMapping("/hechos")
    public Mono<List<HechoDTO>> getHechos() {
        return proxyService.cargarYObtenerHechos();
    }


    @GetMapping("/hechos")
    public List<HechoDTO> obtenerHechosDesde(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde) {


        return proxyService.obtenerDesdeFecha(desde);
    }

}
