package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.Coleccion;
import utn.repositories.ColeccionRepository;

import java.util.List;

@Service
public class AgregadorService {

    private final ColeccionRepository coleccionRepository;

    @Autowired
    public AgregadorService(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    public void actualizarHechosDeTodasLasColecciones() {
        List<Coleccion> colecciones = coleccionRepository.getAll();
        for (Coleccion coleccion : colecciones) {
            coleccion.actualizarHechos();  // Actualiza los hechos de la colección
        }
    }
}
