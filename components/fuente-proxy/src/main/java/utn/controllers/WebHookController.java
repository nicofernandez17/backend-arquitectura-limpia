package utn.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import utn.model.HechoDTO;
import utn.services.clientServices.MetaMapaService;

import java.util.List;

@Controller
@RequestMapping("/webhook")
public class WebHookController {
	private final MetaMapaService metaMapaService;

	public WebHookController(MetaMapaService metaMapaService) {
		this.metaMapaService = metaMapaService;
	}

	@PostMapping("/hechos")
	public ResponseEntity<Void> recibirHechos(@RequestBody List<HechoDTO> hechos) {
		metaMapaService.procesarHechos(hechos);
		return ResponseEntity.ok().build();
	}
}
