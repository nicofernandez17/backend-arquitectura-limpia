package utn.services.components;

import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import utn.model.HechoDTO;
import utn.services.clientServices.MetaMapaService;

import java.util.List;

@Component
public class MetaMapaListener {

	private final MetaMapaService metaMapaService;

	public MetaMapaListener(MetaMapaService metaMapaService) {
		this.metaMapaService = metaMapaService;
	}

	@RabbitListener(queues = "#{miCola.name}")
	public void recibirHechos(List<HechoDTO> hechos) {
		System.out.println("Llegaron hechos al listener de metamapa");
		//metaMapaService.procesarHechos(hechos);
	}

}


