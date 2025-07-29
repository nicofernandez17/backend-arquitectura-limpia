package utn.services.rabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utn.models.dtos.HechoDTO;

import java.util.List;

@Service
public class RabbitPublisher {

    private final RabbitTemplate rabbitTemplate;

    @Value("${app.instancia-id}")
    private String instanciaId;

    public RabbitPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publicar(HechoDTO hecho) {
        String exchangeName = "hechos.exchange." + instanciaId;
        String routingKey = "hecho.nuevo";

        rabbitTemplate.convertAndSend(exchangeName,routingKey, hecho);
    }

    public void publicarHechos(List<HechoDTO> hechos) {
        hechos.forEach(this::publicar);
    }
}
