package utn.configs;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitPublisherConfig {

    @Bean
    public FanoutExchange hechosExchange() {
        return new FanoutExchange("hechos.exchange");
    }
}
