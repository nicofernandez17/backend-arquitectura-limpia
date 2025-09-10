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

@RestController
@RequestMapping("/normalizar")
public class NormalizadorController {

    private final NormalizadorService normalizadorService;

    @Autowired
    public NormalizadorController(NormalizadorService normalizadorService) {
        this.normalizadorService = normalizadorService;
    }

    @PostMapping
    public HechoDTO normalizar(@RequestBody HechoDTO hechoDTO) {
        Hecho hecho = HechoMapper.aDominio(hechoDTO);
        HechoDTO normalizado = HechoMapper.aDTO(normalizadorService.normalizar(hecho));

        return normalizado;
    }

}
