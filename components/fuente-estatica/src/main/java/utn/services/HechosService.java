package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;
import utn.model.lectores.AdapterLectorCsv;
import utn.model.lectores.Lector;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
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

    public List<Hecho> cargarDesdeCsv() {
        List<Hecho> nuevosHechos = lectorCsv.leer(archivoRuta);

        hechosRepository.saveAll(nuevosHechos);

        return hechosRepository.findAll();
    }

    public List<Hecho> obtenerTodos() {
        return hechosRepository.findAll();
    }


    public List<Hecho> obtenerDesdeFecha(LocalDateTime desde) {
        LocalDateTime fecha = desde != null ? desde : LocalDateTime.of(2000, 1, 1, 0, 0);

        return hechosRepository.findAll().stream()
                .filter(h -> h.getCreated_at() != null && h.getCreated_at().isAfter(fecha))
                .toList();
    }
}
