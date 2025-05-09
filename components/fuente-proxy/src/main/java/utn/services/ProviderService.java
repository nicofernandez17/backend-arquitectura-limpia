package utn.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechosResponseDTO;
import utn.model.HechoDTO;

import java.util.List;

@Service
public class ProviderService {

    private final WebClient webClient;

    public ProviderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://catfact.ninja").build();
    }

    public Mono<List<HechoDTO>> getHechos() {
        return webClient.get()
                .uri("/facts")
                .retrieve()
                .bodyToMono(HechosResponseDTO.class)
                .map(HechosResponseDTO::getHechos);
    }
}
