package utn.services;

import org.springframework.stereotype.Service;
import utn.model.HechoDTO;
import utn.model.lectores.AdapterLectorCsv;

import java.util.List;

@Service
public class HechosService {

    private final AdapterLectorCsv lector;

    public HechosService() {
        this.lector = new AdapterLectorCsv();
    }

    public List<HechoDTO> obtenerHechos() {
        String ruta = "components/fuente-estatica/src/main/resources/desastres_naturales_argentina.csv";
        return lector.leer(ruta);
    }
}
