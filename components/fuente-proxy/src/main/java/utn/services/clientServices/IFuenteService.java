package utn.services.clientServices;

import reactor.core.publisher.Mono;
import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;

import java.util.List;

public interface IFuenteService {
    public Mono<List<Hecho>> getHechos();
    public void guardarHechosEnRepositorio(List<Hecho> hechos);

}
