package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.model.HechoDTO;
import utn.services.HechoService;

@RestController
@RequestMapping("/hechos")
public class HechoUIController {

    @Autowired
    private final HechoService hechosUIService;

    public HechoUIController(HechoService hechosUIService) {
        this.hechosUIService = hechosUIService;
    }

    @PostMapping
    public ResponseEntity<String> registrarHecho(@RequestBody HechoDTO hechoDTO) {
        hechosUIService.registrarHecho(hechoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hecho registrado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHecho(@PathVariable Long id, @RequestBody HechoDTO hechoDTO) {
        hechoDTO.setId(id);
        Long idActualizado = hechosUIService.registrarHecho(hechoDTO);

        if (idActualizado != null) {
            return ResponseEntity.ok("Hecho actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hecho no encontrado");
        }
    }
}
