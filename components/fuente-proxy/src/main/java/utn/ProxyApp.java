package utn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("utn.configs")
public class ProxyApp {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApp.class, args);
    }

}
