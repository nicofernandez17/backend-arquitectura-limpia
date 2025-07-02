package utn.controllers.webhook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.services.webhook.WebhookRegistryService;

@RestController
@RequestMapping("/webhooks")
public class WebhookController {

	private final WebhookRegistryService registryService;

	public WebhookController(WebhookRegistryService registryService) {
		this.registryService = registryService;
	}

	@PostMapping("/webhooks/registrar")
	public ResponseEntity<Void> registrar(@RequestBody RegistroSuscriber suscriber) {
		registryService.registrar(suscriber);
		return ResponseEntity.ok().build();
	}

}
