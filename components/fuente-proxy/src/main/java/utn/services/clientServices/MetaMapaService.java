package utn.services.clientServices;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;
import utn.model.HechosResponseDTO;
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


    public Mono<List<HechoDTO>> getHechos() {
        return webClient.get()
                .uri("/hechos")
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

    // Funcion que procesa los hechos que llegan del webhook (agregador externo los pushea)
    public void procesarHechos(List<HechoDTO> hechos) {
        //TODO: esta función debería filtrar los hechos recibidos, y si corresponde enviarlos al Agregador

    }

}
