package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;
import utn.services.MetaMapaProxyService;

import java.util.List;

@RestController
@RequestMapping("/fuente-metamapa")
public class MetaMapaProxyController {

    private final MetaMapaProxyService proxyService;

    public MetaMapaProxyController(MetaMapaProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @PostMapping
    public ResponseEntity<Void> consumirOtraInstancia(@RequestParam String url) {
        proxyService.obtenerHechosDesdeInstancia(url)
                .subscribe(proxyService::enviarHechosAlAgregador);
        return ResponseEntity.ok().build();
    }
}
