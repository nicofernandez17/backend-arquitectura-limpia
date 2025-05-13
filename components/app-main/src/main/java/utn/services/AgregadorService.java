package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.models.dtos.ColeccionDTO;
import utn.repositories.ColeccionRepository;

import java.util.List;

@Service
public class AgregadorService {

    private final WebClient webClient;
    private final ColeccionRepository coleccionRepository;



    @Autowired
    public AgregadorService(WebClient.Builder builder, ColeccionRepository coleccionRepository) {
        this.webClient = builder.baseUrl("http://localhost:8081").build();
        this.coleccionRepository = coleccionRepository;
    }

    public List<ColeccionDTO> obtenerColecciones() {
        return webClient.get()
                .uri("/colecciones")
                .retrieve()
                .bodyToFlux(ColeccionDTO.class)
                .collectList()
                .block();  // Bloqueo si estás en un contexto sincrónico
    }
}
