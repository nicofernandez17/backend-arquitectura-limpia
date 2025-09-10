package utn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UtilsApp {

    public static void main(String[] args) {

        SpringApplication.run(UtilsApp.class, args);

    }




}
