package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;
import utn.repositories.IHechoRepository;
import utn.services.clientServices.DDSService;
import utn.services.clientServices.IFuenteService;
import utn.services.clientServices.MetaMapaService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProxyService {

    private final List<IFuenteService> fuentes;
    private final IHechoRepository hechosRepository;

    public ProxyService(List<IFuenteService> fuentes, IHechoRepository hechosRepository) {
        this.fuentes = fuentes;
        this.hechosRepository = hechosRepository;
    }

    public Mono<List<HechoDTO>> cargarYObtenerHechos() {

        return Flux.fromIterable(fuentes)
                .flatMap(IFuenteService::getHechos)
                .then(Mono.fromCallable(() -> hechosRepository.findAll()));
    }

    public List<HechoDTO> obtenerDesdeFecha(LocalDateTime desde) {
        LocalDateTime fecha = desde != null ? desde : LocalDateTime.of(2000, 1, 1, 0, 0);

        return hechosRepository.findAll().stream()
                .filter(h -> h.getCreated_at() != null && h.getCreated_at().isAfter(fecha))
                .toList();
    }
}