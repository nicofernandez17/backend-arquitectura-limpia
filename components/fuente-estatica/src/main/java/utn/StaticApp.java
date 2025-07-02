package utn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import utn.model.HechoDTO;
import utn.model.lectores.AdapterLectorCsv;
import utn.services.HechosService;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class StaticApp {

    public static void main(String[] args) {

        SpringApplication.run(StaticApp.class, args);

    }




}
