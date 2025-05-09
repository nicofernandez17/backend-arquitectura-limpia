package utn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechosResponseDTO;
import utn.model.HechoDTO;

import java.util.List;

@Service
public class ProviderService {

    private final WebClient webClient;

    public ProviderService(WebClient.Builder webClientBuilder,
                           @Value("${api.ddsi.token}") String token) {
        this.webClient = webClientBuilder
                .baseUrl("https://api-ddsi.disilab.ar/public")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
    }

    public Mono<List<HechoDTO>> getHechos() {
        return webClient.get()
                .uri("/api/desastres")
                .retrieve()
                .bodyToMono(HechosResponseDTO.class)
                .map(HechosResponseDTO::getHechos);
    }
}
