package utn.services.clientServices;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;
import utn.model.dto.HechoMapper;
import utn.model.dto.HechosResponseDTO;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetaMapaService  {
    //TODO
    private final WebClient webClient;
    private final IHechoRepository hechosRepository;


    public MetaMapaService(@Value("${app.url-agregador}") String agregadorUrl,WebClient.Builder webClientBuilder, IHechoRepository hechosRepository) {
        this.webClient = webClientBuilder.baseUrl(agregadorUrl).build();
        this.hechosRepository = hechosRepository;
    }

// TODO revisar esta parte, no me acuerdo por que lo commitee
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

    // Funcion que procesa los hechos que llegan del broker (agregador externo los publica)
    public void procesarHechos(List<HechoDTO> hechos) {

        // TODO filtramos los hechos que son nuevos y los ponemos en esta variable
        List<Hecho> hechosNuevos = hechos.stream().map(HechoMapper::aDominio).toList();

        // Guardamos en el repository solo los hechos nuevos
        hechosNuevos.forEach(hechosRepository::save);

        // Enviamos al agregador los hechos que ingresaron
        webClient.post()
                .uri("/colecciones/consumirMetaMapa")
                .bodyValue(hechosNuevos)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();

    }

    public void procesarHecho(HechoDTO hecho) {

        // TODO filtramos los hechos que son nuevos y los ponemos en esta variable
        Hecho h = HechoMapper.aDominio(hecho);

        // TODO Normalizar hecho antes de guardarlo

        // Guardamos en el repository solo los hechos nuevos
        hechosRepository.save(h);

        // Enviamos al agregador los hechos que ingresaron
        webClient.post()
                .uri("/colecciones/consumirMetaMapa")
                .bodyValue(List.of(hecho))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();

    }

}
