package utn.models.dto;

import utn.models.domain.Hecho;
import utn.models.domain.Ubicacion;
import utn.models.domain.Categoria;

import java.util.Optional;

public class HechoMapper {

    // Dominio → DTO (para API)
    public static HechoDTO aDTO(Hecho hecho) {
        return HechoDTO.builder()
                .id(hecho.getId())  // ID generado por MySQL
                .titulo(hecho.getTitulo())
                .descripcion(hecho.getDescripcion())
                .categoria(Optional.ofNullable(hecho.getCategoria())
                        .map(Categoria::getNombre)
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
                .multimediaPath(null)
                .build();
    }

    // DTO → Dominio
    public static Hecho aDominio(HechoDTO dto) {
        Categoria categoria = null;
        if (dto.getCategoria() != null && !dto.getCategoria().isBlank()) {
            categoria = new Categoria(dto.getCategoria());
        }

        Ubicacion ubicacion = new Ubicacion(dto.getLatitud(), dto.getLongitud());

        return Hecho.builder()
                // No seteamos ID aquí, Hibernate lo generará
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
