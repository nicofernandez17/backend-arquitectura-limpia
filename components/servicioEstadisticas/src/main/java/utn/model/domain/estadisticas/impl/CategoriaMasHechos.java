package utn.model.domain.estadisticas.impl;

import utn.model.domain.Coleccion;
import utn.model.domain.estadisticas.EstadisticasColeccion;
import utn.model.domain.estadisticas.IEstrategiaEstadistica;

import java.util.Map;
import java.util.stream.Collectors;

public class CategoriaMasHechos implements IEstrategiaEstadistica {

    @Override
    public void calcular(Coleccion coleccion, EstadisticasColeccion resultado) {
        String categoria = coleccion.getHechos().stream()
                .filter(h -> !h.isEliminado())
                .collect(Collectors.groupingBy(h -> h.getCategoria().getNombre(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sin datos");
        resultado.setCategoriaMasHechos(categoria);
    }
}