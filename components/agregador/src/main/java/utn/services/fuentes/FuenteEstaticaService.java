package utn.services.fuentes;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuenteEstaticaService implements IFuenteService {

    private final WebClient webClient;

    public FuenteEstaticaService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8083").build();
    }


    public List<Hecho> obtenerHechos() {
        List<Hecho> hechos = webClient.get()
                .uri("/hechos")
                .retrieve()
                .bodyToFlux(HechoDTO.class)
                .collectList()
                .block()
                .stream()
                .map(HechoMapper::aDominio)
                .toList();

        System.out.println(hechos.size());

        return hechos;
    }
}
