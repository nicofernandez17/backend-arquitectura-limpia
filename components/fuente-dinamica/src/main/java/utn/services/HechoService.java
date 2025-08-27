package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utn.model.domain.Hecho;
import utn.model.dtos.HechoDTO;
import utn.model.dtos.HechoMapper;
import utn.repositories.HechoRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HechoService {


    private final HechoRepository hechosRepository;

    @Autowired
    public HechoService(HechoRepository hechosRepository) {
        this.hechosRepository = hechosRepository;
    }


    // Obtiene todos los hechos desde el repositorio
    public List<Hecho> obtenerTodos() {
        return hechosRepository.findAll();
    }

    // Obtiene un hecho por id
    public Optional<Hecho> obtenerPorId(String id) {
        return hechosRepository.findById(id);
    }

    public List<HechoDTO> obtenerDesdeFecha(LocalDateTime desde) {
        LocalDateTime fecha = desde != null ? desde : LocalDateTime.of(2000, 1, 1, 0, 0);

        return hechosRepository.findAll().stream()
                .filter(h -> h.getFechaDeCarga() != null && h.getFechaDeCarga().isAfter(fecha))
                .map(HechoMapper::aDTO)
                .toList();
    }

    // Registra un nuevo hecho en el repositorio
    public String registrarHecho(HechoDTO hechoDTO) {

        return hechosRepository.save(HechoMapper.aDominio(hechoDTO));
    }

    public String actualizarHecho(String id,HechoDTO hechoDTO) {

        return hechosRepository.update(id,HechoMapper.aDominio(hechoDTO));
    }



}
