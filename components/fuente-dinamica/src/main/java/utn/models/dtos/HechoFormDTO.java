package utn.models.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class HechoFormDTO {

    private String titulo;
    private String descripcion;
    private String categoria;
    private double latitud;
    private double longitud;
    private LocalDateTime fecha;
    private String multimediaPath;
}


