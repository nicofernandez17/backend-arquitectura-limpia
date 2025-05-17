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
import utn.repositories.ColeccionRepository;

import java.util.List;

@Service
public class ColeccionService {

    private final ColeccionRepository coleccionRepository;

    @Autowired
    public ColeccionService(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }


    public void actualizarHechosDeTodasLasColecciones() {
        List<Coleccion> colecciones = coleccionRepository.findAll();
        for (Coleccion coleccion : colecciones) {
            coleccion.actualizarHechos();  // Actualiza los hechos de la colección

        }
        System.out.println("Tarea ejecutada a las " + java.time.LocalTime.now());
    }

    public List<Coleccion> obtenerColecciones() {
        return coleccionRepository.findAll();
    }

    public List<Hecho> obtenerHechosPorColeccion(String identificador) {
        return coleccionRepository.findById(identificador).get().getHechos();
    }


}
