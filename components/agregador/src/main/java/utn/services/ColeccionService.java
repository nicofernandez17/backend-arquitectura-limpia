package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
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
        List<Coleccion> colecciones = coleccionRepository.getAll();
        for (Coleccion coleccion : colecciones) {
            coleccion.actualizarHechos();  // Actualiza los hechos de la colección
        }
    }

    public List<Coleccion> obtenerColecciones() {
        coleccionRepository.getAll();
    }

    public List<Hecho> obtenerHechosPorColeccion(String identificador) {
        return coleccionRepository.findById(identificador).get().getHechos();
    }
}
