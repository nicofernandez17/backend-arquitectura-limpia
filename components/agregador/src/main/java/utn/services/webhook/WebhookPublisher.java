package utn.services.webhook;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import utn.models.dtos.HechoDTO;

import java.util.List;

@Service
public class WebhookPublisher {

	private final WebhookRegistryService registryService;
	private final RestTemplate restTemplate = new RestTemplate();

	public WebhookPublisher(WebhookRegistryService registryService) {
		this.registryService = registryService;
	}

	public void enviarHechos(List<HechoDTO> hechos) {
		for (String url : registryService.obtenerUrls()) {
			try {
				restTemplate.postForEntity(url, hechos, Void.class);
				System.out.println("Hechos enviados a fuente: "+ url);
			} catch (Exception e) {
				System.err.println("Error enviando a " + url + ": " + e.getMessage());
			}
		}
	}
}
