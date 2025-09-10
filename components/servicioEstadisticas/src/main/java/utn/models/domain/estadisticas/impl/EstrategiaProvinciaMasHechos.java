package utn.models.domain.estadisticas.impl;

import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.models.domain.estadisticas.EstadisticasColeccion;
import utn.models.domain.estadisticas.IEstrategiaEstadistica;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstrategiaProvinciaMasHechos implements IEstrategiaEstadistica {

    private final GeocodingService geocodingService;

    public EstrategiaProvinciaMasHechos(GeocodingService geo) {
        this.geocodingService = geo;
    }

    @Override
    public void calcular(Coleccion coleccion, EstadisticasColeccion resultado) {
        Map<String, Long> countByProvincia = coleccion.getHechos().stream()
                .collect(Collectors.groupingBy(
                        h -> geocodingService.obtenerProvincia(h.getLatitud(), h.getLongitud()),
                        Collectors.counting()
                ));

        String provinciaMax = countByProvincia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sin datos");

        resultado.setProvinciaMasHechos(provinciaMax);
    }
}