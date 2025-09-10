package utn.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HechoService {

    private final IHechoRepository hechosRepository;

    @Autowired
    public HechoService(IHechoRepository hechosRepository) {
        this.hechosRepository = hechosRepository;
    }

    // Obtiene todos los hechos
    public List<HechoDTO> obtenerTodos() {
        return hechosRepository.findAll().stream()
                .map(HechoMapper::aDTO)
                .toList();
    }

    // Obtiene un hecho por id
    public Optional<HechoDTO> obtenerPorId(Long id) {
        return hechosRepository.findById(id)
                .map(HechoMapper::aDTO);
    }

    // Obtiene hechos desde una fecha determinada
    public List<HechoDTO> obtenerDesdeFecha(LocalDateTime desde) {
        LocalDateTime fecha = desde != null ? desde : LocalDateTime.of(2000, 1, 1, 0, 0);
        return hechosRepository.findByFechaDeCargaAfter(fecha).stream()
                .map(HechoMapper::aDTO)
                .toList();
    }

    // Registra un nuevo hecho
    public HechoDTO registrarHecho(HechoDTO hechoDTO) {
        Hecho hecho = HechoMapper.aDominio(hechoDTO);

        Hecho guardado = hechosRepository.save(hecho);
        return HechoMapper.aDTO(guardado);
    }

    // Actualiza un hecho existente
    public HechoDTO actualizarHecho(Long id, HechoDTO hechoDTO) {
        Hecho actualizado = hechosRepository.findById(id)
                .map(h -> {
                    HechoMapper.updateDominio(h, hechoDTO); // Copia los campos del DTO
                    return hechosRepository.save(h);
                })
                .orElseThrow(() -> new EntityNotFoundException("Hecho no encontrado con id: " + id));

        return HechoMapper.aDTO(actualizado);
    }

    // Eliminar un hecho
    public void eliminarHecho(Long id) {
        if (!hechosRepository.existsById(id)) {
            throw new EntityNotFoundException("Hecho no encontrado con id: " + id);
        }
        hechosRepository.deleteById(id);
    }
}
