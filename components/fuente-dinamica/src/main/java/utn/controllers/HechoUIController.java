package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utn.model.HechoDTO;
import utn.services.HechoService;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/hechos")
public class HechoUIController {

    @Autowired
    private final HechoService hechosUIService;

    public HechoUIController(HechoService hechosUIService) {
        this.hechosUIService = hechosUIService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> registrarHecho(
            @RequestParam String titulo,
            @RequestParam String descripcion,
            @RequestParam String categoria,
            @RequestParam double latitud,
            @RequestParam double longitud,
            @RequestParam("fecha_hecho") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaHecho,
            @RequestParam(required= false) MultipartFile archivo) {
        HechoDTO hechoDTO = HechoDTO.builder()
                        .titulo(titulo)
                        .descripcion(descripcion)
                        .categoria(categoria)
                        .latitud(latitud)
                        .longitud(longitud)
                        .fecha_hecho(fechaHecho)
                        .archivo(archivo)
                        .build();

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

    @GetMapping("/{id}/archivo")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable Long id) {
        Optional<HechoDTO> hecho = hechosUIService.obtenerPorId(id);

        if (hecho.isEmpty() || hecho.get().getArchivoContenido() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + hecho.get().getArchivoNombre() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(hecho.get().getArchivoContenido());
    }

}
