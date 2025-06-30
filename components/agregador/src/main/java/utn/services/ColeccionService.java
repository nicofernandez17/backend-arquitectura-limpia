package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.ColeccionRepository;
import utn.repositories.HechoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ColeccionService {

    private final ColeccionRepository coleccionRepository;
    private final HechoRepository hechoRepository;

    @Autowired
    public ColeccionService(ColeccionRepository coleccionRepository, HechoRepository hechoRepository) {
        this.coleccionRepository = coleccionRepository;
        this.hechoRepository = hechoRepository;
    }

    // ===== CREAR =====
    public void crearColeccion(String titulo, String descripcion) {
        Coleccion coleccion = Coleccion.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .hechos(new ArrayList<>())
                .criteriosDePertenencia(new ArrayList<>())
                .build();
        coleccionRepository.save(coleccion);
    }

    // ===== LEER =====
    public List<Coleccion> obtenerColecciones() {
        return coleccionRepository.findAll();
    }

    public Optional<Coleccion> obtenerColeccionPorId(String id) {
        return coleccionRepository.findById(id);
    }

    public List<Hecho> obtenerHechosPorColeccion(String idColeccion) {
        return coleccionRepository.findById(idColeccion)
                .map(Coleccion::getHechos)
                .orElse(Collections.emptyList());
    }

    // ===== ACTUALIZAR =====
    public void actualizarColecciones() {
        List<Hecho> todosLosHechos = hechoRepository.findAll();
        coleccionRepository.findAll().forEach(coleccion -> {
            coleccion.actualizarHechos(todosLosHechos);
        });
    }

    public void actualizarColeccion(String id, String nuevoTitulo, String nuevaDescripcion) {
        coleccionRepository.findById(id).ifPresent(coleccion -> {
            coleccion.setTitulo(nuevoTitulo);
            coleccion.setDescripcion(nuevaDescripcion);
            coleccionRepository.save(coleccion);
        });
    }

    public void agregarHechos(List<HechoDTO> hechosDTO) {
        List<Coleccion> colecciones = coleccionRepository.findAll();
        List<Hecho> hechos = hechosDTO.stream()
                .map(HechoMapper::aDominio)
                .toList();
        for (Coleccion coleccion : colecciones) {
            coleccion.agregarHechos(hechos);
        }
        System.out.println("Hechos agregados a las colecciones");
    }

    // ===== ELIMINAR =====
    public void eliminarColeccion(String id) {
        coleccionRepository.delete(id);
    }

    public void eliminarTodos() {
        coleccionRepository.deleteAll();
    }
}
