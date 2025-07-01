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

import java.util.ArrayList;
import java.util.List;

@Service
public class ProxyService {


    private final DDSService ddsService;
    private final MetaMapaService metaMapaService;
    private final IHechoRepository hechosRepository;
    private final List<IFuenteService> apisExternas;

    public ProxyService(DDSService ddsService,
                        MetaMapaService metaMapaService,
                        IHechoRepository hechosRepository) {
        this.ddsService = ddsService;
        this.metaMapaService = metaMapaService;
        this.hechosRepository = hechosRepository;
        this.apisExternas = new ArrayList<>();
    }

    public Mono<List<HechoDTO>> cargarYObtenerHechos() {
        return Mono.when(
                ddsService.getHechos()
                //metaMapaService.getHechos()
        ).then(Mono.fromCallable(() -> hechosRepository.findAll()));
    }

    /* Lo mismo que la de arriba pero agarra los hechos de la lista de apis externas,
     que debería tener las otras fuentes dentro */
    public Mono<List<HechoDTO>> cargarYObtenerHechosV2() {
        return Flux.fromIterable(apisExternas)
                .flatMap(IFuenteService::getHechos) // ejecuta getHechos() para cada fuente
                .then(Mono.fromCallable(() -> hechosRepository.findAll()));
    }
}