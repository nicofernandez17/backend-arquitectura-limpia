package utn.configs;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


@Configuration
public class RabbitConfig {

	@Value("${app.instancia-id}")
	private String instanciaId;

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}

	@Bean
	public Queue miCola() {
		return new Queue("hechos_queue_" + instanciaId, true);
	}

	@Bean
	public FanoutExchange miExchange() {
		return new FanoutExchange("hechos.fanout");
	}

	@Bean
	public Binding binding(Queue miCola, FanoutExchange miExchange) {
		return BindingBuilder.bind(miCola).to(miExchange);
	}
}
