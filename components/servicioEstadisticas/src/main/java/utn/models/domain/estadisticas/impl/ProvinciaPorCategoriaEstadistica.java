package utn.models.domain.estadisticas.impl;

public class ProvinciaPorCategoriaEstadistica implements IEstrategiaEstadistica {

    private final String categoriaObjetivo;

    public ProvinciaPorCategoriaEstadistica(String categoriaObjetivo) {
        this.categoriaObjetivo = categoriaObjetivo;
    }

    @Override
    public String getNombre() {
        return "Provincia con más hechos de categoría " + categoriaObjetivo;
    }

    @Override
    public String calcular(Coleccion coleccion) {
        return coleccion.getHechos().stream()
                .filter(h -> h.getCategoria() != null && h.getCategoria().getNombre().equalsIgnoreCase(categoriaObjetivo))
                .filter(h -> h.getUbicacion() != null && h.getUbicacion().getProvincia() != null)
                .collect(Collectors.groupingBy(h -> h.getUbicacion().getProvincia(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }
}