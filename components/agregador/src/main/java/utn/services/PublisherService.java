package utn.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import utn.models.dtos.HechoDTO;

import java.util.List;

@Service
public class PublisherService {
	private final RabbitTemplate rabbitTemplate;

	public PublisherService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void enviarMensaje(String mensaje) {
		rabbitTemplate.convertAndSend("hechos.fanout", "", mensaje);
	}

	public void enviarHechos(List<HechoDTO> hechos) {
		rabbitTemplate.convertAndSend("hechos.fanout", "", hechos);
	}
}
