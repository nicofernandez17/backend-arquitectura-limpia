package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utn.model.HechoDTO;
import utn.model.lectores.AdapterLectorCsv;
import utn.model.lectores.Lector;
import utn.repositories.IHechoRepository;

import java.util.List;

@Service
public class HechosService {

    private final IHechoRepository hechosRepository;
    private final Lector lectorCsv;

    @Value("${archivo.datos.path}")  // Inyectar el valor de la propiedad
    private String archivoRuta;

    @Autowired
    public HechosService(IHechoRepository hechosRepository) {
        this.hechosRepository = hechosRepository;
        this.lectorCsv = new AdapterLectorCsv();
    }

    public List<HechoDTO> cargarDesdeCsv() {
        List<HechoDTO> nuevosHechos = lectorCsv.leer(archivoRuta);

        nuevosHechos.forEach(hechosRepository::save);

        return hechosRepository.findAll();
    }

    public List<HechoDTO> obtenerTodos() {
        return hechosRepository.findAll();
    }


}
