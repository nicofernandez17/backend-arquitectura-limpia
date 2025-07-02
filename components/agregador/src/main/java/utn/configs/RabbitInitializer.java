package utn.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.stereotype.Component;

@Component
public class RabbitInitializer {

	private final RabbitAdmin rabbitAdmin;
	private final Queue miCola;
	private final FanoutExchange miExchange;
	private final Binding binding;

	public RabbitInitializer(RabbitAdmin rabbitAdmin, Queue miCola, FanoutExchange miExchange, Binding binding) {
		this.rabbitAdmin = rabbitAdmin;
		this.miCola = miCola;
		this.miExchange = miExchange;
		this.binding = binding;
	}

	@PostConstruct
	public void init() {
		System.out.println(">> Declarando recursos RabbitMQ...");
		rabbitAdmin.declareQueue(miCola);
		rabbitAdmin.declareExchange(miExchange);
		rabbitAdmin.declareBinding(binding);
	}
}
