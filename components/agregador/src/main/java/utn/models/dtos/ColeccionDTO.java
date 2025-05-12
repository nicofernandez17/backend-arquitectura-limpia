package utn.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ColeccionDTO {
    private String id;            // ID de la colección
    private String titulo;      // Título de la colección
    private List<HechoDTO> hechos;  // Lista de hechos en formato HechoDTO
}