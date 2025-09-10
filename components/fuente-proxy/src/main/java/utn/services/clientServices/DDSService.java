package utn.services.clientServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.models.domain.Hecho;
import utn.models.dto.HechoMapper;
import utn.models.dto.HechosResponseDTO;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
import java.util.List;

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






