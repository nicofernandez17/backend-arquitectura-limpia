package utn.controllers.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import utn.controllers.seeding.ColeccionSeeder;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.modosDeNavegacion.IModoDeNavegacion;
import utn.models.modosDeNavegacion.ModoDeNavegacionFactory;
import utn.services.ColeccionService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/colecciones")
public class ColeccionController {

    private final ColeccionService coleccionService;
    private final ColeccionSeeder coleccionSeeder;
    private final ModoDeNavegacionFactory modoDeNavegacionFactory;

    @Autowired
    public ColeccionController(ColeccionService coleccionService,
                               ColeccionSeeder coleccionSeeder,
                               ModoDeNavegacionFactory modoDeNavegacionFactory) {
        this.coleccionService = coleccionService;
        this.coleccionSeeder = coleccionSeeder;
        this.modoDeNavegacionFactory = modoDeNavegacionFactory;
    }

    @GetMapping
    public List<ColeccionDTO> obtenerColecciones() {
        List<Coleccion> colecciones = coleccionService.obtenerColecciones();
        return colecciones.stream()
                .map(ColeccionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ColeccionDTO obtenerColeccion(@PathVariable Long id) {
        Coleccion coleccion = coleccionService.obtenerColeccionPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrada"));

        return ColeccionMapper.toDTO(coleccion);
    }

    @GetMapping("/{identificador}/hechos")
    public List<HechoDTO> obtenerHechosPorColeccion(
            @PathVariable Long identificador,
            @RequestParam(defaultValue = "curado") String modo) {

        Coleccion coleccion = coleccionService.obtenerColeccionPorId(identificador)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrada"));

        IModoDeNavegacion estrategia = modoDeNavegacionFactory.obtener(modo);
        List<Hecho> hechos = estrategia.obtenerHechos(coleccion);

        return hechos.stream()
                .map(HechoMapper::aDTO)
                .toList();
    }

    @PostMapping("/inicializar")
    public ResponseEntity<Void> inicializarColecciones() {
        coleccionSeeder.seed();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/consumirMetaMapa")
    public ResponseEntity<String> recibirHechosDesdeProxy(@RequestBody List<HechoDTO> hechos) {
        coleccionService.agregarHechos(hechos);
        return ResponseEntity.status(HttpStatus.CREATED).body("Hechos agregados");
    }

    @PostMapping
    public ResponseEntity<String> crearColeccion(@RequestBody ColeccionDTO coleccion) {
        coleccionService.crearColeccion(coleccion);
        return ResponseEntity.status(HttpStatus.CREATED).body("Colección creada con éxito");
    }
}

