package utn.services;

import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.domain.estadisticas.EstadisticasColeccion;
import utn.models.domain.estadisticas.IEstrategiaEstadistica;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadisticaService {

    private final AgregadorClient agregadorClient;
    private final List<IEstrategiaEstadistica> estrategias;

    public EstadisticaService(AgregadorClient agregadorClient,
                              List<IEstrategiaEstadistica> estrategias) {
        this.agregadorClient = agregadorClient;
        this.estrategias = estrategias;
    }

    /**
     * Calcula las estadísticas para todas las colecciones obtenidas del agregador.
     */
    public List<EstadisticasColeccion> calcularEstadisticasParaTodas() {
        List<Coleccion> colecciones = agregadorClient.obtenerColecciones();

        return colecciones.stream()
                .map(this::calcularEstadisticas)
                .collect(Collectors.toList());
    }

    /**
     * Calcula las estadísticas para una colección específica aplicando todas las estrategias.
     */
    public EstadisticasColeccion calcularEstadisticas(Coleccion coleccion) {
        EstadisticasColeccion resultado = EstadisticasColeccion.builder()
                .coleccionId(coleccion.getId())
                .coleccionTitulo(coleccion.getTitulo())
                .calculadoEn(LocalDateTime.now())
                .build();

        // Aplicar todas las estrategias
        estrategias.forEach(e -> e.calcular(coleccion, resultado));

        return resultado;
    }
}

