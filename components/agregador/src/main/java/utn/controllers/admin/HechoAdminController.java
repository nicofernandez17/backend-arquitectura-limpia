package utn.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.IHechoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/admin/hechos")
public class HechoAdminController {

    private final IHechoRepository hechoRepository;

    public HechoAdminController(IHechoRepository hechoRepository) {
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
