package utn.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class HechosResponseDTO {
    private int current_page;
    private List<HechoDTO> data;

    public List<HechoDTO> getHechos() {
        return data;
    }
}
