package utn.services.fuentes;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;

import java.util.List;

@Service
public class FuenteProxyService implements IFuenteService {

    private final WebClient webClient;

    public FuenteProxyService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8084").build();
    }


    public List<Hecho> obtenerHechos() {
        return webClient.get()
                .uri("/hechos")
                .retrieve()
                .bodyToFlux(HechoDTO.class)
                .collectList()
                .block()
                .stream()
                .map(HechoMapper::aDominio)
                .toList();
    }
}
