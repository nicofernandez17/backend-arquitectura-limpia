package utn.schedulers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.models.domain.Coleccion;
import utn.services.AgregadorService;
import utn.services.ColeccionService;

@Component
public class FuenteScheduler {

    private ColeccionService coleccionService;
    private AgregadorService agregadorService;

    public FuenteScheduler(ColeccionService coleccionService,AgregadorService agregadorService) {
        this.coleccionService = coleccionService;
        this.agregadorService = agregadorService;
    }

    @Async
    @Scheduled(cron = "0 */2 * * * *")
    public void actualizarColecciones() {
        agregadorService.cargarHechosYAsignar();


        System.out.println("Actualizando colecciones");

    }

}
