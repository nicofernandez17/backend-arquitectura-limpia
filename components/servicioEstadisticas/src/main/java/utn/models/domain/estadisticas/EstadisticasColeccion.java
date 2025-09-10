package utn.models.domain.estadisticas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadisticasColeccion {
    private String coleccionId;
    private String coleccionTitulo;

    private String categoriaMasHechos;
    private String provinciaMasHechos;
    private Map<String, String> provinciaPorCategoria; // categoria → provincia con más hechos
    private Map<String, Integer> horaPorCategoria;     // categoria → hora del día con más hechos
    private int solicitudesSpam;

    private LocalDateTime calculadoEn;
}
