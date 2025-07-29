package utn.configs;

import org.springframework.amqp.core.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfig {

    @Value("${metamapa.instancia-id}")
    private String instanciaId;

    @Bean
    public TopicExchange hechosExchange() {
        return new TopicExchange("hechos.exchange."+instanciaId,true, false);
    }

    @Bean
    public Queue hechosQueue() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding hechosBinding(Queue hechosQueue, TopicExchange hechosExchange) {
        return BindingBuilder.bind(hechosQueue).to(hechosExchange).with("#");
    }
}
