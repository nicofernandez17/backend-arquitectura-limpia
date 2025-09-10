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
public class EstrategiaHoraPorCategoria implements IEstrategiaEstadistica {

    @Override
    public void calcular(Coleccion coleccion, EstadisticasColeccion resultado) {
        Map<String, Integer> horaPorCategoria = new HashMap<>();

        Map<String, List<Hecho>> hechosPorCategoria = coleccion.getHechos().stream()
                .collect(Collectors.groupingBy(h -> h.getCategoria().getNombre()));

        for (Map.Entry<String, List<Hecho>> entry : hechosPorCategoria.entrySet()) {
            String categoria = entry.getKey();
            List<Hecho> hechos = entry.getValue();

            Integer horaMax = hechos.stream()
                    .collect(Collectors.groupingBy(
                            h -> h.getFecha().getHour(),
                            Collectors.counting()
                    ))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(-1);

            horaPorCategoria.put(categoria, horaMax);
        }

        resultado.setHoraPorCategoria(horaPorCategoria);
    }
}
