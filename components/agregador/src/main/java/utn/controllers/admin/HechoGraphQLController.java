package utn.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.dtos.input.HechoFiltroInput;
import utn.services.ColeccionService;
import utn.services.HechoService;

import java.util.List;

@Controller
public class HechoGraphQLController {

    private final HechoService hechoService;
    private final ColeccionService coleccionService;

    @Autowired
    public HechoGraphQLController(HechoService hechoService, ColeccionService coleccionService) {
        this.hechoService = hechoService;
        this.coleccionService = coleccionService;
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
    public List<HechoDTO> hechosFiltrados(
            @Argument HechoFiltroInput filtro,
            @Argument Integer idColeccion
    ) {
        // Obtener los hechos que pertenecen a la colección
        List<HechoDTO> hechosEnColeccion = coleccionService
                .obtenerHechosPorColeccion(Long.valueOf(idColeccion))
                .stream()
                .map(HechoMapper::aDTO)
                .toList();

        // Aplicar filtros si existen
        return hechosEnColeccion.stream()
                .filter(h -> filtro.getCategoria() == null || h.getCategoria().equalsIgnoreCase(filtro.getCategoria()))
                .filter(h -> filtro.getFechaDesde() == null || !h.getFecha_hecho().isBefore(filtro.getFechaDesde()))
                .filter(h -> filtro.getFechaHasta() == null || !h.getFecha_hecho().isAfter(filtro.getFechaHasta()))
                .toList();
    }
}