package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.model.HechoDTO;
import utn.repositories.HechosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HechoService {

    private final HechosRepository hechosRepository;

    @Autowired
    public HechosService(HechosRepository hechosRepository) {
        this.hechosRepository = hechosRepository;
    }

    // Obtiene todos los hechos desde el repositorio
    public List<HechoDTO> obtenerHechos() {
        return hechosRepository.findAll();
    }

    // Registra un nuevo hecho en el repositorio
    public void registrarHecho(HechoDTO hechoDTO) {
        hechosRepository.save(hechoDTO);
    }

    // Actualiza un hecho existente en el repositorio
    public boolean actualizarHecho(Long id, HechoDTO hechoDTO) {
        Optional<HechoDTO> hechoExistente = hechosRepository.findById(id);

        if (hechoExistente.isPresent()) {
            HechoDTO hecho = hechoExistente.get();
            hecho.setTitulo(hechoDTO.getTitulo());
            hecho.setDescripcion(hechoDTO.getDescripcion());
            hecho.setCategoria(hechoDTO.getCategoria());
            hecho.setLatitud(hechoDTO.getLatitud());
            hecho.setLongitud(hechoDTO.getLongitud());
            hecho.setFecha_hecho(hechoDTO.getFecha_hecho());

            hechosRepository.save(hecho);
            return true;
        } else {
            return false;  // No se encuentra el hecho
        }
    }

    // Otros métodos si es necesario (por ejemplo, eliminar hechos, buscar por parámetros específicos, etc.)
}
