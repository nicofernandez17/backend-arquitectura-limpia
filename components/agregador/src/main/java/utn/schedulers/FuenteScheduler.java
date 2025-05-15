package utn.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.models.domain.Coleccion;
import utn.services.ColeccionService;

@Component
public class FuenteScheduler {

    private ColeccionService coleccionService;

    public FuenteScheduler(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void actualizarColecciones() {
        coleccionService.actualizarHechosDeTodasLasColecciones();

    }

}
