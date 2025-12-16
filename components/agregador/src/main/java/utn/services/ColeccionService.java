package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import utn.models.algoritmos.AlgoritmoConsensoResolver;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.ColeccionDTO;
import utn.models.dtos.ColeccionMapper;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.IColeccionRepository;
import utn.repositories.IHechoRepository;

import java.util.*;

@Service
public class ColeccionService {

    private final IColeccionRepository coleccionRepository;
    private final IHechoRepository hechoRepository;
    private final AlgoritmoConsensoResolver algoritmoConsensoResolver;

    @Autowired
    public ColeccionService(IColeccionRepository coleccionRepository, IHechoRepository hechoRepository, AlgoritmoConsensoResolver algoritmoConsensoResolver) {
        this.coleccionRepository = coleccionRepository;
        this.hechoRepository = hechoRepository;
        this.algoritmoConsensoResolver = algoritmoConsensoResolver;
    }

    // ===== CREAR =====
    public void crearColeccion(ColeccionDTO coleccionDTO) {
        // 🔹 Todo el mapeo lo hace el mapper
        Coleccion coleccion = ColeccionMapper.toDomain(coleccionDTO,algoritmoConsensoResolver);

        coleccionRepository.save(coleccion);
    }

    // ===== LEER =====
    public List<Coleccion> obtenerColecciones() {
        return coleccionRepository.findAll();
    }

    public Optional<Coleccion> obtenerColeccionPorId(Long id) {
        return coleccionRepository.findById(id);
    }

    public List<Hecho> obtenerHechosPorColeccion(Long idColeccion) {
        return coleccionRepository.findById(idColeccion)
                .map(c -> new ArrayList<>(c.getHechos()))
                .orElseGet(ArrayList::new);
    }

    public List<Hecho> obtenerHechosIrrestricto(Long id) {
        Coleccion coleccion = coleccionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrada"));

        return new ArrayList<>(coleccion.getHechos()); // CAMBIO
    }

    public List<Hecho> obtenerHechosCurado(Long id) {
        Coleccion coleccion = coleccionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrada"));

        return new ArrayList<>(coleccion.getHechosFiltradosPorConsenso()); // CAMBIO
    }

    // ===== ACTUALIZAR =====
    public void actualizarColecciones() {
        List<Hecho> todosLosHechos = hechoRepository.findAll();
        coleccionRepository.findAll().forEach(coleccion -> {
            coleccion.actualizarHechos(todosLosHechos);
        });
    }

    public void actualizarColeccion(Long id, String nuevoTitulo, String nuevaDescripcion) {
        coleccionRepository.findById(id).ifPresent(coleccion -> {
            coleccion.setTitulo(nuevoTitulo);
            coleccion.setDescripcion(nuevaDescripcion);
            coleccionRepository.save(coleccion);
        });
    }

    public void agregarHechos(List<HechoDTO> hechosDTO) {

        List<Coleccion> colecciones = coleccionRepository.findAll();

        // Notar: generamos objetos Hecho desde DTO
        List<Hecho> hechos = hechosDTO.stream()
                .map(HechoMapper::aDominio)
                .toList();

        for (Coleccion coleccion : colecciones) {
            // NO DUPLICA MÁS porque el dominio usa Set
            coleccion.agregarHechos(hechos);
        }

        System.out.println("Hechos agregados a las colecciones");
    }

    // ===== ELIMINAR =====
    public void eliminarColeccion(Long id) {
        coleccionRepository.deleteById(id);
    }

    public void eliminarTodos() {
        coleccionRepository.deleteAll();
    }
}
