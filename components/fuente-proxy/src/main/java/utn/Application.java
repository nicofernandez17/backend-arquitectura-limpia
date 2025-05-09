package utn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.services.ProviderService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private final ProviderService providerService;

    public Application(ProviderService providerService) {
        this.providerService = providerService;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        providerService.getHechos()
                .doOnNext(hechos -> hechos.forEach(h -> System.out.println(h.getDescripcion()))) // o cualquier campo que tenga tu HechoDTO
                .block(); // Espera la respuesta del Mono para que el programa no termine antes
    }
}
