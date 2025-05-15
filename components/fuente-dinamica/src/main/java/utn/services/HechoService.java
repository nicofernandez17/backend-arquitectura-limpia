package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.model.HechoDTO;
import utn.repositories.IHechoDTORepository;

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

    // Registra un nuevo hecho en el repositorio
    public Long registrarHecho(HechoDTO hechoDTO) {
        return hechosRepository.save(hechoDTO);
    }



}
