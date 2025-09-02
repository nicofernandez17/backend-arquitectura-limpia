package utn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StaticApp {

    public static void main(String[] args) {

        SpringApplication.run(StaticApp.class, args);

    }




}
