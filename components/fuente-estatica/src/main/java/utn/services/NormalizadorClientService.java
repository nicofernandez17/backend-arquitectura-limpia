package utn.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import utn.models.domain.Hecho;
import utn.models.dto.HechoDTO;
import utn.models.dto.HechoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class NormalizadorClientService {

    private final WebClient webClient;

    public NormalizadorClientService(WebClient.Builder webClientBuilder,
                                     @Value("${utils.url}") String utilsUrl) {
        this.webClient = webClientBuilder.
                baseUrl(utilsUrl)
                .build();
    }

    public List<Hecho> normalizarHechos(List<Hecho> hechos) {
        List<Hecho> resultado = new ArrayList<>();
        int batchSize = 1000;

        for (int i = 0; i <= hechos.size(); i+=batchSize) {
            List<Hecho> batch = hechos.subList(i, Math.min(i + batchSize, hechos.size()));
            List<HechoDTO> batchDTO = batch.stream().map(HechoMapper::aDTO).toList();

            System.out.println("Enviando lote de " + batchDTO.size() + " hechos a normalizar");

            List<Hecho> batchNormalizado = webClient.post()
                            .uri("/normalizar")
                            .bodyValue(batchDTO)
                    .retrieve()
                    .bodyToFlux(HechoDTO.class)
                    .map(HechoMapper::aDominio)
                    .collectList()
                    .block();

            resultado.addAll(batchNormalizado);

        }

        return resultado;
    }
}
