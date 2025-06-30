package utn.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.HechoRepository;

import java.util.List;

@RestController
@RequestMapping("/admin/hechos")
public class HechoAdminController {

    private final HechoRepository hechoRepository;

    public HechoAdminController(HechoRepository hechoRepository) {
        this.hechoRepository = hechoRepository;
    }

    @GetMapping
    public List<HechoDTO> obtenerTodos() {
        return hechoRepository.findAll().stream()
                .map(HechoMapper::aDTO)
                .toList();
    }

    private boolean coincideTitulo(String tituloHecho, String filtro) {
        return tituloHecho != null && tituloHecho.toLowerCase().contains(filtro.toLowerCase());
    }
}
