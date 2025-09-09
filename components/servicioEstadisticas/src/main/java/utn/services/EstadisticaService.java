package utn.services;

import org.springframework.stereotype.Service;
import utn.model.domain.Coleccion;
import utn.model.domain.Hecho;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadisticaService {

    private final AgregadorClient agregadorClient;

    public EstadisticaService(AgregadorClient agregadorClient) {
        this.agregadorClient = agregadorClient;
    }

    public void calcularEstadisticas() {
        List<Coleccion> colecciones = agregadorClient.obtenerColecciones(); // dominio, no DTO
        List<Hecho> todosLosHechos = colecciones.stream()
                .flatMap(c -> c.getHechos().stream())
                .collect(Collectors.toList());


    }
}
