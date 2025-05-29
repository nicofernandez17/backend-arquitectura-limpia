package utn.services.clientServices;

import reactor.core.publisher.Mono;
import utn.model.HechoDTO;

import java.util.List;

public interface IFuenteService {
    public Mono<List<HechoDTO>> getHechos();
    public void guardarHechosEnRepositorio(List<HechoDTO> hechos);

}
