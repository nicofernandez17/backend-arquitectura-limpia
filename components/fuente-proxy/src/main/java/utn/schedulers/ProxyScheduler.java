package utn.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.services.ProxyService;

@Component
public class ProxyScheduler {

    private final ProxyService proxyService;

    public ProxyScheduler(ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @Scheduled(fixedRate = 600000) // cada 10 minutos
    public void cargarCSV() {
        System.out.println("⏱ Consumiendo APIs...");
        proxyService.cargarYObtenerHechos().block();
    }
}