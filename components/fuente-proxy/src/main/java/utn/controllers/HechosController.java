package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;
import utn.services.ProviderService;

import java.util.List;

@RestController
public class HechosController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/hechos")
    public Mono<List<HechoDTO>> getHechos() {
        return providerService.getHechos()
                .flatMap(hechos -> providerService.obtenerHechos());
    }

}
