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


    public MetaMapaService(@Value("${app.url-agregador}") String agregadorUrl,WebClient.Builder webClientBuilder, IHechoRepository hechosRepository) {
        this.webClient = webClientBuilder.baseUrl(agregadorUrl).build();
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

    // Funcion que procesa los hechos que llegan del broker (agregador externo los publica)
    public void procesarHechos(List<HechoDTO> hechos) {

        // TODO filtramos los hechos que son nuevos y los ponemos en esta variable
        List<HechoDTO> hechosNuevos = hechos;

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

}
