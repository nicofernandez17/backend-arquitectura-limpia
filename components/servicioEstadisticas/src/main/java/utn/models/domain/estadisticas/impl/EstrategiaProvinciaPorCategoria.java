package utn.models.domain.estadisticas.impl;

import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.domain.estadisticas.EstadisticasColeccion;
import utn.models.domain.estadisticas.IEstrategiaEstadistica;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstrategiaProvinciaPorCategoria implements IEstrategiaEstadistica {

    private final GeocodingService geocodingService;

    public EstrategiaProvinciaPorCategoria(GeocodingService geo) {
        this.geocodingService = geo;
    }

    @Override
    public void calcular(Coleccion coleccion, EstadisticasColeccion resultado) {
        Map<String, String> provinciaPorCategoria = new HashMap<>();

        Map<String, List<Hecho>> hechosPorCategoria = coleccion.getHechos().stream()
                .collect(Collectors.groupingBy(h -> h.getCategoria().getNombre()));

        for (Map.Entry<String, List<Hecho>> entry : hechosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Hecho> hechos = entry.getValue();

            String provinciaMax = hechos.stream()
                    .collect(Collectors.groupingBy(
                            h -> geocodingService.obtenerProvincia(h.getLatitud(), h.getLongitud()),
                            Collectors.counting()
                    ))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("Sin datos");

            provinciaPorCategoria.put(categoria, provinciaMax);
        }

        resultado.setProvinciaPorCategoria(provinciaPorCategoria);
    }
}
