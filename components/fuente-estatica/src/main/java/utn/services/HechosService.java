package utn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import utn.models.domain.Hecho;
import utn.models.lectores.AdapterLectorCsv;
import utn.models.lectores.Lector;
import utn.repositories.IHechoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HechosService {

    private final NormalizadorClientService normalizadorClientService;
    private final IHechoRepository hechosRepository;
    private final Lector lectorCsv;

    @Value("${archivo.datos.path}")  // Inyectar el valor de la propiedad
    private String archivoRuta;

    @Autowired
    public HechosService(NormalizadorClientService normalizadorClientService, IHechoRepository hechosRepository) {
        this.normalizadorClientService = normalizadorClientService;
        this.hechosRepository = hechosRepository;
        this.lectorCsv = new AdapterLectorCsv();
    }

    @Async
    public List<Hecho> cargarDesdeCsv() {
        List<Hecho> nuevosHechos = lectorCsv.leer(archivoRuta);

        List<Hecho> normalizados = normalizadorClientService.normalizarHechos(nuevosHechos);

        hechosRepository.saveAll(normalizados);

        return hechosRepository.findAll();
    }

    @Async
    public List<Hecho> cargarDesdeCsv(String ruta) {
        List<Hecho> nuevosHechos = lectorCsv.leer(ruta);

        List<Hecho> normalizados = normalizadorClientService.normalizarHechos(nuevosHechos);

        hechosRepository.saveAll(normalizados);

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
