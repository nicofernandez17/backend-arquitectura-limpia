package utn.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ColeccionDTO {
    private String id;            // ID de la colección
    private String titulo;      // Título de la colección
    private String descripcion;
    private List<HechoDTO> hechos;  // Lista de hechos en formato HechoDTO
}
