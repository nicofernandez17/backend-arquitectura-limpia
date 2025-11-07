package utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utn.models.dto.HechoDTO;
import utn.models.dto.HechoMapper;
import utn.services.HechosService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/hechos")
public class HechosController {

    private final HechosService hechosService;

    @Autowired
    public HechosController(HechosService hechosService) {
        this.hechosService = hechosService;
    }


    @PostMapping
    public ResponseEntity<String> cargarDesdeCsv() {
        hechosService.cargarDesdeCsv();
        return ResponseEntity.ok("Se inicio la carga y procesamiento de los hechos.");
    }

    @PostMapping("/cargar")
    public ResponseEntity<String> subirCsvDesdeFront(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo CSV está vacío");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String linea;
            int contador = 0;

            while ((linea = reader.readLine()) != null) {
                // ⚙️ Acá procesás cada línea del CSV
                System.out.println("Procesando línea " + (++contador) + ": " + linea);

                // Por ejemplo, podrías parsearla:
                // String[] campos = linea.split(",");
                // Heladera heladera = new Heladera(campos[0], campos[1], ...);
                // repositorioHeladera.save(heladera);
            }

            return ResponseEntity.ok("Archivo CSV procesado correctamente. Líneas leídas: " + contador);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Error al procesar el archivo CSV: " + e.getMessage());
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
