package utn.services.clientServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechosResponseDTO;
import utn.model.HechoDTO;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DDSService implements IFuenteService {

    private final WebClient webClient;
    private final IHechoRepository hechosRepository;  // Inyección del repositorio

    public DDSService(WebClient.Builder webClientBuilder,
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

    public void guardarHechosEnRepositorio(List<HechoDTO> hechos) {
        LocalDateTime ahora = LocalDateTime.now();
        for (HechoDTO hecho : hechos) {
            hecho.setCreated_at(ahora);  // Setea la fecha actual
            hechosRepository.save(hecho);  // Guarda el hecho en el repositorio
        }
    }



}
