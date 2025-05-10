package utn.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import utn.models.HechoDTO;

import java.util.List;

@Service
public class FuenteService {

    private final WebClient webClient;

    public FuenteService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8083").build();
    }

    public List<HechoDTO> obtenerHechos() {
        return webClient.get()
                .uri("/hechos")
                .retrieve()
                .bodyToFlux(HechoDTO.class)
                .collectList()
                .block();
    }
}
