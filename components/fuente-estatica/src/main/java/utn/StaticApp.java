package utn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import utn.model.HechoDTO;
import utn.model.lectores.AdapterLectorCsv;

import java.util.List;

@SpringBootApplication
public class StaticApp {

    public static void main(String[] args) {

        SpringApplication.run(StaticApp.class, args);

    }




}
