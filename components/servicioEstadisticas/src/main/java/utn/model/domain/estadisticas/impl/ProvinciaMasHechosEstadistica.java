package utn.model.domain.estadisticas.impl;

import utn.model.domain.Coleccion;

import java.util.Map;
import java.util.stream.Collectors;

public class ProvinciaMasHechosEstadistica implements IEstrategiaEstadistica {

    @Override
    public String getNombre() {
        return "Provincia con más hechos";
    }

    @Override
    public String calcular(Coleccion coleccion) {
        return coleccion.getHechos().stream()
                .filter(h -> h.getUbicacion() != null && h.getUbicacion().getProvincia() != null)
                .collect(Collectors.groupingBy(h -> h.getUbicacion().getProvincia(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }
}