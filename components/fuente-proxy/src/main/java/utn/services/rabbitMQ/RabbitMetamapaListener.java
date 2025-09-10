package utn.services.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import utn.models.dto.HechoDTO;
import utn.services.clientServices.MetaMapaService;

@Service
public class RabbitMetamapaListener {

    private final MetaMapaService hechoService;

    public RabbitMetamapaListener(MetaMapaService hechoService) {
        this.hechoService = hechoService;
    }

    @RabbitListener(queues = "#{hechosQueue.name}")
    public void recibirHecho(HechoDTO hecho) {
        hechoService.procesarHecho(hecho); // guarda si no existe, y envía al agregador.
    }
}
