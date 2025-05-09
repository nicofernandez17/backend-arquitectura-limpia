package utn.model;

import lombok.Data;

import java.util.List;

@Data
public class HechosResponseDTO {
    private List<HechoDTO> hechos;
}
