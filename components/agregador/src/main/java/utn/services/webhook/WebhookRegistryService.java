package utn.services.webhook;

import org.springframework.stereotype.Service;
import utn.controllers.webhook.RegistroSuscriber;

import java.util.*;

@Service
public class WebhookRegistryService {

	private final Set<RegistroSuscriber> suscriptores = new HashSet<>();

	public void registrar(RegistroSuscriber suscriber) {
		suscriptores.add(suscriber);
		System.out.println("Se registro suscriber: " + suscriber.getInstanciaId());
	}

	public Set<RegistroSuscriber> obtenerSuscriptores() {
		return Collections.unmodifiableSet(suscriptores);
	}

	public List<String> obtenerUrls() {
		return suscriptores.stream()
				.map(RegistroSuscriber::getCallbackUrl)
				.toList();
	}

}
