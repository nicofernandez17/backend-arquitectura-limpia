package utn.controllers.publics;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.criterios.ICriterioDePertenencia;
import utn.models.criterios.CriterioFiltroTitulo;
import utn.models.criterios.CriterioPorCategoria;
import utn.models.criterios.CriterioPorFecha;
import utn.models.criterios.CriterioPorUbicacion;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.helpers.Categoria;
import utn.models.helpers.Ubicacion;
import utn.repositories.IHechoRepository;
import utn.services.ColeccionService;

import java.util.List;

@RestController
@RequestMapping("/api/hechos")
public class HechosController {

    private final ColeccionService coleccionService;
    private final IHechoRepository hechoRepository;

    public HechosController(ColeccionService coleccionService, IHechoRepository hechoRepository) {
        this.coleccionService = coleccionService;
        this.hechoRepository = hechoRepository;
    }

    // --- GET /api/hechos ---
    @GetMapping
    public List<HechoDTO> obtenerTodosLosHechos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_acontecimiento_desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_acontecimiento_hasta,
            @RequestParam(required = false) Double latitud,
            @RequestParam(required = false) Double longitud,
            @RequestParam(required = false) String titulo
    ) {
        List<ICriterioDePertenencia> criterios = new ArrayList<>();

        if (categoria != null) {
            criterios.add(new CriterioPorCategoria(new Categoria(categoria)));
        }
        if (fecha_acontecimiento_desde != null && fecha_acontecimiento_hasta != null) {
            criterios.add(new CriterioPorFecha(fecha_acontecimiento_desde, fecha_acontecimiento_hasta));
        }
        if (latitud != null && longitud != null) {
            criterios.add(new CriterioPorUbicacion(new Ubicacion(latitud, longitud)));
        }
        if (titulo != null) {
            criterios.add(new CriterioFiltroTitulo(titulo));
        }

        return coleccionService.obtenerColecciones().stream()
                .flatMap(coleccion -> coleccion.getHechos().stream())
                .filter(hecho -> criterios.stream().allMatch(c -> c.cumple(hecho)))
                .map(HechoMapper::aDTO)
                .toList();
    }

    // --- GET /api/hechos/{id} ---
    @GetMapping("/{id}")
    public ResponseEntity<HechoDTO> obtenerHechoPorId(@PathVariable Long id) {
        return hechoRepository.findById(id)
                .map(HechoMapper::aDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
