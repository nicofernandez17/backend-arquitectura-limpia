package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import utn.model.HechoDTO;
import utn.repositories.IHechoRepository;
import utn.services.clientServices.DDSService;
import utn.services.clientServices.MetaMapaService;

import java.util.List;

@Service
public class ProxyService {


    private final DDSService ddsService;
    private final MetaMapaService metaMapaService;
    private final IHechoRepository hechosRepository;

    public ProxyService(DDSService ddsService,
                              MetaMapaService metaMapaService,
                              IHechoRepository hechosRepository) {
        this.ddsService = ddsService;
        this.metaMapaService = metaMapaService;
        this.hechosRepository = hechosRepository;
    }

    public Mono<List<HechoDTO>> cargarYObtenerHechos() {
        return Mono.when(
                ddsService.getHechos()
                //metaMapaService.getHechos()
        ).then(Mono.fromCallable(() -> hechosRepository.findAll()));
    }
}