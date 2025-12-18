package utn.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import utn.services.ProxyService;

@Component
public class ProxyScheduler {

    private ProxyService proxyService;

    public ProxyScheduler(ProxyService proxyService) {
        this.proxyService = proxyService;
    }


    @Scheduled(cron = "0 */4 * * * *")
    public void cargarCSV() {
        System.out.println("Consumiendo API'S");

        proxyService.cargarYObtenerHechos();
        

    }

}