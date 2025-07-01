package utn.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.services.ConsensoService;

@Component
public class ConsensoScheduler {

    private final ConsensoService consensoService;

    public ConsensoScheduler(ConsensoService consensoService) {
        this.consensoService = consensoService;
    }


    @Scheduled(cron = "0 0 * * * * " )
    public void ejecutarConsensoProgramado() {
        consensoService.aplicarConsensoPorColeccion();
    }
}
