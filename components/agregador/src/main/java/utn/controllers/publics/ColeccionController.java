package utn.controllers.publics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.controllers.seeding.ColeccionSeeder;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.services.ColeccionService;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colecciones")
public class ColeccionController {

    private final ColeccionService coleccionService;
    private final ColeccionSeeder coleccionSeeder;

    @Autowired
    public ColeccionController(ColeccionService coleccionService, ColeccionSeeder coleccionSeeder) {
        this.coleccionService = coleccionService;
        this.coleccionSeeder = coleccionSeeder;
    }


    @GetMapping
    public List<ColeccionDTO> obtenerColecciones() {
        // Obtener colecciones desde el servicio
        List<Coleccion> colecciones = coleccionService.obtenerColecciones();

        // Convertir cada Coleccion a ColeccionDTO usando el ColeccionMapper
        return colecciones.stream()
                .map(ColeccionMapper::toDTO)  // Usamos el mapper para convertir a DTO
                .collect(Collectors.toList());
    }

    @GetMapping("/{identificador}/hechos")
    public List<HechoDTO> obtenerHechosPorColeccion(@PathVariable String identificador) {
        List<Hecho> hechos = coleccionService.obtenerHechosPorColeccion(identificador);
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
        coleccionService.crearColeccion(coleccion.getTitulo(), coleccion.getDescripcion());

        return ResponseEntity.status(HttpStatus.CREATED).body("Coleccion creada con exito");
    }

}
