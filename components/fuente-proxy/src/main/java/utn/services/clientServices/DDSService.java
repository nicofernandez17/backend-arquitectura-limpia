package utn.services.clientServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;
import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;
import utn.model.dto.HechoMapper;
import utn.model.dto.HechosResponseDTO;
import utn.repositories.IHechoRepository;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.stream.Collectors;

@Service
public class DDSService implements IFuenteService {

    private final WebClient webClient;
    private final IHechoRepository hechosRepository;

    public DDSService(WebClient.Builder webClientBuilder,
                      @Value("${api.ddsi.token}") String token,
                      IHechoRepository hechosRepository) {
        this.webClient = webClientBuilder
                .baseUrl("https://api-ddsi.disilab.ar/public")
                .defaultHeader("Authorization", "Bearer " + token)
                .build();
        this.hechosRepository = hechosRepository;
    }

    @Override
    public Mono<List<Hecho>> getHechos() {
        return webClient.get()
                .uri("/api/desastres")
                .retrieve()
                .bodyToMono(HechosResponseDTO.class)
                .map(dto -> dto.getHechos().stream()
                        .map(HechoMapper::aDominio)
                        .toList()
                )
                .doOnNext(this::guardarHechosEnRepositorio);
    }

    @Override
    public void guardarHechosEnRepositorio(List<Hecho> hechos) {
        LocalDateTime ahora = LocalDateTime.now();
        for (Hecho hecho : hechos) {
            hecho.setCreated_at(ahora);
            // TODO: aplicar normalización si hace falta
            hechosRepository.save(hecho);
            System.out.println("💾 Guardado hecho: " + hecho.getTitulo());
        }
    }
}






