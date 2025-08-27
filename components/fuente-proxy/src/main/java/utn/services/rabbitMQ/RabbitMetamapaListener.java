package utn.services.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import utn.model.dto.HechoDTO;
import utn.services.clientServices.MetaMapaService;

import java.util.List;

@Service
public class RabbitMetamapaListener {

    private final MetaMapaService hechoService;

    public RabbitMetamapaListener(MetaMapaService hechoService) {
        this.hechoService = hechoService;
    }

    @RabbitListener(queues = "#{hechosQueue.name}")
    public void recibirHecho(List<HechoDTO> hechos) {
        hechoService.procesarHechos(hechos); // guarda si no existe, y envía al agregador.
    }
}
