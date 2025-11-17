package utn.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.dtos.input.HechoFiltroInput;
import utn.services.HechoService;

import java.util.List;

@Controller
public class HechoGraphQLController {

    private final HechoService hechoService;

    @Autowired
    public HechoGraphQLController(HechoService hechoService) {
        this.hechoService = hechoService;
    }

    @QueryMapping
    public List<HechoDTO> hechos() {
        return hechoService.obtenerHechos()
                .stream()
                .map(HechoMapper::aDTO)
                .toList();
    }

    @QueryMapping
    public HechoDTO hechoPorId(@Argument String id) {
        return hechoService.obtenerHechoById(Long.valueOf(id))
                .map(HechoMapper::aDTO)
                .orElse(null);
    }

    @QueryMapping
    public List<HechoDTO> hechosFiltrados(@Argument HechoFiltroInput filtro) {
        return hechoService.buscarPorFiltros(filtro)
                .stream()
                .map(HechoMapper::aDTO)
                .toList();
    }
}