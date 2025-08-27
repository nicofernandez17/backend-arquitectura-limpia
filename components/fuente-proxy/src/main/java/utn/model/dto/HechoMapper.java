package utn.model.dto;

import utn.model.domain.Hecho;
import utn.model.domain.Ubicacion;
import utn.model.domain.Categoria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class HechoMapper {

    // Dominio → DTO (para API)
    public static HechoDTO aDTO(Hecho hecho) {
        return HechoDTO.builder()
                .titulo(hecho.getTitulo())
                .descripcion(hecho.getDescripcion())
                .categoria(Optional.ofNullable(hecho.getCategoria().getNombre())
                        .map(Object::toString)
                        .orElse(null))
                .latitud(Optional.ofNullable(hecho.getUbicacion())
                        .map(Ubicacion::getLatitud)
                        .orElse(0.0))
                .longitud(Optional.ofNullable(hecho.getUbicacion())
                        .map(Ubicacion::getLongitud)
                        .orElse(0.0))
                .fecha_hecho(hecho.getFecha())
                .created_at(hecho.getCreated_at())
                .updated_at(hecho.getUpdated_at())
                .archivoContenido(null)
                .archivoNombre(null)
                .build();
    }

    // DTO → Dominio
    public static Hecho aDominio(HechoDTO dto) {
        Categoria categoria = null;
        String categoriaStr = dto.getCategoria();

        if (categoriaStr != null && !categoriaStr.isBlank()) {
            categoria = new Categoria(categoriaStr);
        }

        Ubicacion ubicacion = new Ubicacion(dto.getLatitud(), dto.getLongitud());

        return Hecho.builder()
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .categoria(categoria)
                .ubicacion(ubicacion)
                .fecha(dto.getFecha_hecho())
                .created_at(dto.getCreated_at())
                .updated_at(dto.getUpdated_at())
                .build();
    }
}
