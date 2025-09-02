package utn.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.services.HechosService;

@Component
public class EstaticaScheduler {

    private HechosService hechosService;

    public EstaticaScheduler(HechosService hechosService) {
        this.hechosService = hechosService;
    }


    //@Scheduled(cron = "1 * * * * *")
    public void cargarCSV() {
        System.out.println("Cargando csv");

        hechosService.cargarDesdeCsv();



    }

}