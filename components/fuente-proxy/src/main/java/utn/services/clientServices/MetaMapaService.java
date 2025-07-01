package utn.services.clientServices;

import org.reactivestreams.Publisher;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;
import utn.model.HechosResponseDTO;
import utn.repositories.IHechoRepository;

import java.util.List;

@Service
public class MetaMapaService implements IFuenteService {
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
        for (HechoDTO hecho : hechos) {
            hechosRepository.save(hecho);  // Guardar cada HechoDTO en el repositorio
        }
    }

    @RabbitListener(queues = "#{miCola.name}")
    public void recibirMensaje(String mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
    }

}
