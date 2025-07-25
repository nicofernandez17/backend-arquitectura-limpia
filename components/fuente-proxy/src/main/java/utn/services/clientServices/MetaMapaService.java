package utn.services.clientServices;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;
import utn.model.dto.HechosResponseDTO;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetaMapaService  {
    //TODO
    private final WebClient webClient;
    private final IHechoRepository hechosRepository;


    public MetaMapaService(WebClient.Builder webClientBuilder, IHechoRepository hechosRepository) {
        this.webClient = webClientBuilder.baseUrl("").build();
        this.hechosRepository = hechosRepository;
    }


    /*public Mono<List<HechoDTO>> getHechos() {
        return webClient.get()
                .uri("/hechos")
                .retrieve()
                .bodyToMono(HechosResponseDTO.class)
                .map(HechosResponseDTO::getHechos)
                .doOnNext(this::guardarHechosEnRepositorio);  // Guardar los hechos en el repositorio
    }*/

    public void guardarHechosEnRepositorio(List<Hecho> hechos) {
        LocalDateTime ahora = LocalDateTime.now();
        for (Hecho hecho : hechos) {
            hecho.setCreated_at(ahora);  // Setea la fecha actual
            hechosRepository.save(hecho);  // Guarda el hecho en el repositorio
        }
    }
}
