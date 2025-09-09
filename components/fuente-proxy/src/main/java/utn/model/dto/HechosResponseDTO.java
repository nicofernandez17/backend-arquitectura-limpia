package utn.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class HechosResponseDTO {
    private int current_page;
    private List<HechoDTO> data;
    private String next_page_url;  // para paginación
    private String prev_page_url;
    private int last_page;
    private int per_page;
    private int total;

    // Getter de conveniencia para mantener compatibilidad
    public List<HechoDTO> getHechos() {
        return data;
    }
}
