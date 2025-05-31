package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utn.model.domain.Hecho;
import utn.model.dtos.HechoDTO;
import utn.model.dtos.HechoMapper;
import utn.repositories.HechoRepository;
import utn.repositories.IHechoDTORepository;

import java.io.IOException;
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

    // Registra un nuevo hecho en el repositorio
    public String registrarHecho(HechoDTO hechoDTO) {
        MultipartFile archivo = hechoDTO.getArchivo();

        if(archivo != null && !archivo.isEmpty()) {
            try {
                hechoDTO.setArchivoContenido(archivo.getBytes());
                hechoDTO.setArchivoNombre(archivo.getOriginalFilename());
            } catch (IOException e) {
                throw new RuntimeException("Error al leer el archivo", e);
            }
        }

        return hechosRepository.save(HechoMapper.aDominio(hechoDTO));
    }



}
