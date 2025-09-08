package utn.configs;

import org.springframework.amqp.core.*;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Value("${metamapa.instancia-id}")
    private String instanciaId;

    @Bean
    public TopicExchange hechosExchange() {
        return new TopicExchange("hechos.exchange."+instanciaId,true, false);
    }

    @Bean
    public Queue hechosQueue() {
        /* En esta parte puede cambiarse el AnonymousQueue por otro tipo al que le asignemos un id,
        para que se sepa quien está suscrito al exchange desde la webApp de RabbitMQ */
        return new AnonymousQueue();
    }

    @Bean
    public Binding hechosBinding(Queue hechosQueue, TopicExchange hechosExchange) {
        return BindingBuilder.bind(hechosQueue).to(hechosExchange).with("#");
    }
}
