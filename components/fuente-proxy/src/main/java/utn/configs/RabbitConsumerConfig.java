package utn.configs;

import lombok.Value;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfig {

    @Value("${metamapa.instancia-id}")
    private String instanciaId;

    @Bean
    public FanoutExchange hechosExchange() {
        return new FanoutExchange("hechos.exchange");
    }

    @Bean
    public Queue hechosQueue() {
        return new Queue("hechos." + instanciaId, true);
    }

    @Bean
    public Binding hechosBinding(Queue hechosQueue, FanoutExchange hechosExchange) {
        return BindingBuilder.bind(hechosQueue).to(hechosExchange);
    }
}
