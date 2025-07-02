package utn.services.webhook;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Component
public class SubscriberRegistrar {

	@Value("${app.instancia-id}")
	private String instanciaId;

	@Value("${app.webhook-url}")
	private String webhookUrl;

	@Value("${app.url-metamapa}")
	private String urlAgregador;

	@PostConstruct
	public void registrarEnAgregador() {
		RestTemplate restTemplate = new RestTemplate();

		// Armar el cuerpo del request como JSON
		Map<String, String> body = new HashMap<>();
		body.put("instanciaId", instanciaId);
		body.put("callbackUrl", webhookUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

		try {
			restTemplate.postForEntity(urlAgregador, request, String.class);
			System.out.println("✅ Registrado en el agregador con instanciaId=" + instanciaId + ", url=" + webhookUrl);
		} catch (Exception e) {
			System.err.println("❌ Error registrando webhook: " + e.getMessage());
		}
	}
}
