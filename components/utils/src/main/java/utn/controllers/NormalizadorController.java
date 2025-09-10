package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.domain.Hecho;
import utn.models.dto.HechoDTO;
import utn.models.dto.HechoMapper;
import utn.services.NormalizadorService;

import java.util.List;

@RestController
@RequestMapping("/normalizar")
public class NormalizadorController {

    private final NormalizadorService normalizadorService;

    @Autowired
    public NormalizadorController(NormalizadorService normalizadorService) {
        this.normalizadorService = normalizadorService;
    }

    @PostMapping
    public List<HechoDTO> normalizar(@RequestBody List<HechoDTO> hechosDTO) {
        List<Hecho> hechos = hechosDTO.stream().map(HechoMapper::aDominio).toList();

        System.out.println("Normalizando " + hechos.size() + " hechos");

        List<Hecho> normalizados = hechos.stream().map(normalizadorService::normalizar).toList();

        return normalizados.stream().map(HechoMapper::aDTO).toList();
    }

}
