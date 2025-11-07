package utn.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.helpers.FuenteNombre;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/config")
public class ConfigAdminController {

    private final Map<String, IAlgoritmoConsenso> algoritmos;

    public ConfigAdminController(Map<String, IAlgoritmoConsenso> algoritmos) {
        this.algoritmos = algoritmos;
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

}
