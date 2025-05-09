package utn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechosResponseDTO;
import utn.model.HechoDTO;
import utn.repositories.IHechoRepository;

import java.util.List;

@Service
public class ProviderService {

    private final WebClient webClient;
    private final IHechoRepository hechosRepository;  // Inyección del repositorio

    public ProviderService(WebClient.Builder webClientBuilder,
                           @Value("${api.ddsi.token}") String token,
                           IHechoRepository hechosRepository) {
        this.webClient = webClientBuilder
                .baseUrl("https://api-ddsi.disilab.ar/public")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
        this.hechosRepository = hechosRepository;
    }

    public Mono<List<HechoDTO>> getHechos() {
        return webClient.get()
                .uri("/api/desastres")
                .retrieve()
                .bodyToMono(HechosResponseDTO.class)
                .map(HechosResponseDTO::getHechos)
                .doOnNext(this::guardarHechosEnRepositorio);  // Guardar los hechos en el repositorio
    }

    private void guardarHechosEnRepositorio(List<HechoDTO> hechos) {
        for (HechoDTO hecho : hechos) {
            hechosRepository.save(hecho);  // Guardar cada HechoDTO en el repositorio
        }
    }

    public Mono<List<HechoDTO>> obtenerHechos() {
        return Mono.just(hechosRepository.findAll());  // Obtener todos los hechos desde el repositorio y devolverlo envuelto en Mono
    }

    public HechoDTO obtenerHechoPorTitulo(String titulo) {
        return hechosRepository.findByTitulo(titulo).orElse(null);  // Buscar un Hecho por título
    }
}
