package utn.models.algoritmos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utn.models.helpers.ConsensoNivel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AlgoritmoConsensoResolver {

    private final List<IAlgoritmoConsenso> algoritmos;

    @Autowired
    public AlgoritmoConsensoResolver(List<IAlgoritmoConsenso> algoritmos) {
        this.algoritmos = algoritmos;
    }

    public IAlgoritmoConsenso resolverPorNombreClase(String nombreClase) {
        return algoritmos.stream()
                .filter(a -> a.getClass().getSimpleName().equals(nombreClase))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe algoritmo de consenso: " + nombreClase
                        )
                );
    }
}