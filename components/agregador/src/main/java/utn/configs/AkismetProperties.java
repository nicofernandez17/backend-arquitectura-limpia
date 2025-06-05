package utn.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "akismet")
@Data
public class AkismetProperties {

    private String apiKey;
    private String blogUrl;


}