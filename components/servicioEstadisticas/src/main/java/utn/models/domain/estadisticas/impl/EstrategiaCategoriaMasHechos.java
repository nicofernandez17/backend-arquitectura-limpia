package utn.models.domain.estadisticas.impl;

import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.models.domain.estadisticas.EstadisticasColeccion;
import utn.models.domain.estadisticas.IEstrategiaEstadistica;
import utn.repositories.IHechoRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EstrategiaCategoriaMasHechos implements IEstrategiaEstadistica {

    private final IHechoRepository hechoRepo;

    public EstrategiaCategoriaMasHechos(IHechoRepository hechoRepo) {
        this.hechoRepo = hechoRepo;
    }

    @Override
    public void calcular(Coleccion coleccion, EstadisticasColeccion resultado) {
        Map<String, Long> countByCategoria = coleccion.getHechos().stream()
                .collect(Collectors.groupingBy(
                        h -> h.getCategoria().getNombre(),
                        Collectors.counting()
                ));

        String categoriaMax = countByCategoria.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Sin datos");

        resultado.setCategoriaMasHechos(categoriaMax);
    }
}