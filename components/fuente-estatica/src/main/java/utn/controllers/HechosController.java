package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utn.models.dto.HechoDTO;
import utn.models.dto.HechoMapper;
import utn.services.CsvStorageService;
import utn.services.HechosService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechosController {

    private final HechosService hechosService;
    private final CsvStorageService csvStorageService;

    @Autowired
    public HechosController(HechosService hechosService, CsvStorageService csvStorageService) {
        this.hechosService = hechosService;
        this.csvStorageService = csvStorageService;
    }


    @PostMapping
    public ResponseEntity<String> cargarDesdeCsv() {
        hechosService.cargarDesdeCsv();
        return ResponseEntity.ok("Se inicio la carga y procesamiento de los hechos.");
    }

    @PostMapping("/cargar")
    public ResponseEntity<String> subirCsvDesdeFront(@RequestParam("file") MultipartFile archivo) {
        try {
            String ruta = csvStorageService.guardarArchivo(archivo);
            hechosService.cargarDesdeCsv(ruta);

            return ResponseEntity.ok("Archivo guardado en: " + ruta + " - Se inicio la carga y procesamiento de los hechos.");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @GetMapping
    public List<HechoDTO> obtenerHechosDesde(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde) {


        return hechosService.obtenerDesdeFecha(desde).stream().map(HechoMapper :: aDTO).toList();
    }

}
