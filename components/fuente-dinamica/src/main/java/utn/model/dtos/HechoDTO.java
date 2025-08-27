package utn.model.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
public class HechoDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
    private double latitud;
    private double longitud;
    private LocalDateTime fecha_hecho;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private byte[] archivoContenido;
    private String archivoNombre;
// TODO revisar este dato porque no coincide con el HechoDTO que usamos en Agregador
    private String usuarioId;

}