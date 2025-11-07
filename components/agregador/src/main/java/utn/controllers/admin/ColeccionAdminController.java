package utn.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.helpers.FuenteNombre;
import utn.services.ColeccionService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/colecciones")
public class ColeccionAdminController {

    private final ColeccionService coleccionService;

    @Autowired
    public ColeccionAdminController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    // ======= CREAR =======
    @PostMapping
    public ResponseEntity<String> crear(@RequestParam String titulo, @RequestParam String descripcion) {
        coleccionService.crearColeccion(titulo, descripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body("Colección creada correctamente");
    }

    // ======= LEER =======
    @GetMapping
    public ResponseEntity<List<ColeccionDTO>> obtenerTodas() {
        List<ColeccionDTO> coleccionesDTO = coleccionService.obtenerColecciones().stream()
                .map(ColeccionMapper::toDTO)
                .toList();

        return ResponseEntity.ok(coleccionesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColeccionDTO> obtenerPorId(@PathVariable Long id) {
        return coleccionService.obtenerColeccionPorId(id)
                .map(ColeccionMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/hechos")
    public ResponseEntity<List<HechoDTO>> obtenerHechosPorColeccion(@PathVariable Long id) {
        List<HechoDTO> hechosDTO = coleccionService.obtenerHechosPorColeccion(id).stream()
                .map(HechoMapper::aDTO)
                .toList();

        return ResponseEntity.ok(hechosDTO);
    }

    // ======= ACTUALIZAR =======
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(
            @PathVariable Long id,
            @RequestParam String nuevoTitulo,
            @RequestParam String nuevaDescripcion) {
        coleccionService.actualizarColeccion(id, nuevoTitulo, nuevaDescripcion);
        return ResponseEntity.ok("Colección actualizada");
    }

    // ======= ELIMINAR =======
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPorId(@PathVariable Long id) {
        coleccionService.eliminarColeccion(id);
        return ResponseEntity.ok("Colección eliminada");
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarTodas() {
        coleccionService.eliminarTodos();
        return ResponseEntity.ok("Todas las colecciones eliminadas");
    }

    // ======= FUENTES =======
    @PatchMapping("/{id}/fuentes")
    public ResponseEntity<String> modificarFuente(
            @PathVariable Long id,
            @RequestParam FuenteNombre fuente,
            @RequestParam String operacion) {

        return coleccionService.obtenerColeccionPorId(id).map(coleccion -> {
            String op = operacion.toUpperCase();

            switch (op) {
                case "AGREGAR":
                    if (coleccion.getFuentes().contains(fuente)) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body("La fuente ya está en la colección");
                    }
                    coleccion.getFuentes().add(fuente);
                    return ResponseEntity.ok("Fuente agregada");

                case "QUITAR":
                    if (coleccion.getFuentes().remove(fuente)) {
                        return ResponseEntity.ok("Fuente quitada");
                    }
                    return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                            .body("La fuente no estaba en la colección");

                default:
                    return ResponseEntity.badRequest().body("Operación inválida. Usar AGREGAR o QUITAR");
            }

        }).orElse(ResponseEntity.notFound().build());
    }



}

