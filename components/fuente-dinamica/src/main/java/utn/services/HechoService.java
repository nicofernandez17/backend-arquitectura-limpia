package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utn.model.HechoDTO;
import utn.repositories.IHechoDTORepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HechoService {


    private final IHechoDTORepository hechosRepository;

    @Autowired
    public HechoService(IHechoDTORepository hechosRepository) {
        this.hechosRepository = hechosRepository;
    }


    // Obtiene todos los hechos desde el repositorio
    public List<HechoDTO> obtenerTodos() {
        return hechosRepository.findAll();
    }

    // Obtiene un hecho por id
    public Optional<HechoDTO> obtenerPorId(Long id) {
        return hechosRepository.findById(id);
    }

    // Registra un nuevo hecho en el repositorio
    public Long registrarHecho(HechoDTO hechoDTO) {
        MultipartFile archivo = hechoDTO.getArchivo();

        if(archivo != null && !archivo.isEmpty()) {
            try {
                hechoDTO.setArchivoContenido(archivo.getBytes());
                hechoDTO.setArchivoNombre(archivo.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException("Error al leer el archivo", e);
            }
        }

        return hechosRepository.save(hechoDTO);
    }



}
