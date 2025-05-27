package utn.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;

import java.util.List;

@Service
public class MetaMapaProxyService {

    private final WebClient webClient;

    public MetaMapaProxyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();  // No necesita baseUrl fija
    }

    public Mono<List<HechoDTO>> obtenerHechosDesdeInstancia(String instanciaUrl) {
        return webClient.get()
                .uri(instanciaUrl + "/hechos")
                .retrieve()
                .bodyToFlux(HechoDTO.class)
                .collectList();
    }

    public void enviarHechosAlAgregador(List<HechoDTO> hechos) {
        webClient.post()
                .uri("http://localhost:8081/colecciones/consumirMetaMapa")
                .bodyValue(hechos)
                .retrieve()
                .toBodilessEntity()
                .subscribe();  // ejecuta la solicitud
    }
}

