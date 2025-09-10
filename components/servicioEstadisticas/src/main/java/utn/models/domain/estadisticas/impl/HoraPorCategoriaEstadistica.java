package utn.models.domain.estadisticas.impl;

public class HoraPorCategoriaEstadistica implements IEstrategiaEstadistica {

    private final String categoriaObjetivo;

    public HoraPorCategoriaEstadistica(String categoriaObjetivo) {
        this.categoriaObjetivo = categoriaObjetivo;
    }

    @Override
    public String getNombre() {
        return "Hora con más hechos de categoría " + categoriaObjetivo;
    }

    @Override
    public Integer calcular(Coleccion coleccion) {
        return coleccion.getHechos().stream()
                .filter(h -> h.getCategoria() != null && h.getCategoria().getNombre().equalsIgnoreCase(categoriaObjetivo))
                .filter(h -> h.getFecha() != null)
                .collect(Collectors.groupingBy(h -> h.getFecha().getHour(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(-1);
    }
}