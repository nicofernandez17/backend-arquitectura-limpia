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
import java.util.List;

@Service
public class ColeccionService {

    private final ColeccionRepository coleccionRepository;
    private final HechoRepository hechoRepository;

    @Autowired
    public ColeccionService(ColeccionRepository coleccionRepository, HechoRepository hechoRepository) {
        this.coleccionRepository = coleccionRepository;
        this.hechoRepository = hechoRepository;
    }

    public List<Coleccion> obtenerColecciones() {
        return coleccionRepository.findAll();
    }

    public List<Hecho> obtenerHechosPorColeccion(String identificador) {
        return coleccionRepository.findById(identificador).get().getHechos();
    }

    public void actualizarColecciones() {
        List<Hecho> todosLosHechos = hechoRepository.findAll();

        coleccionRepository.findAll().forEach(coleccion -> {
            coleccion.actualizarHechos(todosLosHechos);
        });
    }

    public void agregarHechos (List<HechoDTO> hechosDTO) {
        List<Coleccion> colecciones = coleccionRepository.findAll();
        for (Coleccion coleccion : colecciones) {
            coleccion.agregarHechos(hechosDTO.stream().map(HechoMapper::aDominio).toList());
        }
        System.out.println("Hechos agregados a las colecciones");
    }

    public void crearColeccion(String titulo, String descripcion) {
        Coleccion coleccion = Coleccion.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .hechos(new ArrayList<>())
                .criteriosDePertenencia(new ArrayList<>())
                .build();
        coleccionRepository.save(coleccion);
    }
}
