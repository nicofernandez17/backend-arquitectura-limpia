package utn.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.dtos.ColeccionDTO;
import utn.models.helpers.FuenteNombre;
import utn.services.ColeccionService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/config")
public class ConfigAdminController {

    private final Map<String, IAlgoritmoConsenso> algoritmos;
    private final ColeccionService coleccionService;

    public ConfigAdminController(Map<String, IAlgoritmoConsenso> algoritmos, ColeccionService coleccionService) {
        this.algoritmos = algoritmos;
        this.coleccionService = coleccionService;
    }


    @GetMapping("/algoritmos")
    public List<String> getAlgoritmos() {
        return algoritmos.values().stream()
                .map(a -> a.getClass().getSimpleName())
                .sorted()
                .toList();
    }


    @GetMapping("/fuentes")
    public List<String> getFuentes() {
        return Arrays.stream(FuenteNombre.values())
                .map(Enum::name)
                .toList();
    }

    @PutMapping("colecciones/{id}")
    public ResponseEntity<String> actualizar(@RequestBody ColeccionDTO coleccionDTO) {
        System.out.println(coleccionDTO);
        coleccionService.actualizarColeccion(coleccionDTO);



        return ResponseEntity.ok("Colección actualizada");
    }

}
