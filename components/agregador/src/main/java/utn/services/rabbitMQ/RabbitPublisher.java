package utn.services.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import utn.models.dtos.HechoDTO;

import java.util.List;

@Service
public class RabbitPublisher {

    private final RabbitTemplate rabbitTemplate;

    public RabbitPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicar(HechoDTO hecho) {
        rabbitTemplate.convertAndSend("hechos.exchange", "", hecho);
    }

    public void publicarHechos(List<HechoDTO> hechos) {
        hechos.forEach(this::publicar);
    }
}
