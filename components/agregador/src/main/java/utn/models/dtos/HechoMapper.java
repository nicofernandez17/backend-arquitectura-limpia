package utn.models.dtos;

import utn.models.domain.Hecho;
import utn.models.helpers.Ubicacion;
import utn.models.helpers.Categoria;
import utn.models.helpers.Origen;

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
                .fecha_hecho(Optional.ofNullable(hecho.getFecha())
                        .map(LocalDate::atStartOfDay)  // LocalDate → LocalDateTime a inicio de día
                        .orElse(null))
                .created_at(Optional.ofNullable(hecho.getFechaDeCarga())
                        .map(LocalDate::atStartOfDay)
                        .orElse(null))
                .updated_at(Optional.ofNullable(hecho.getUpdated_at())
                        .map(LocalDate::atStartOfDay)
                        .orElse(null))
                .archivoContenido(hecho.getMultimediaArchivo())
                .archivoNombre(hecho.getMultimediaNombre())
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

        // Convertir LocalDateTime a LocalDate (tomamos sólo la fecha)
        LocalDate fecha = dto.getFecha_hecho() != null ? dto.getFecha_hecho().toLocalDate() : null;
        LocalDate fechaDeCarga = dto.getCreated_at() != null ? dto.getCreated_at().toLocalDate() : LocalDate.now();

        Hecho hecho = new Hecho(dto.getTitulo(),
                dto.getDescripcion(),
                categoria,
                ubicacion,
                fecha,
                fechaDeCarga,
                Origen.API);
        hecho.setMultimediaArchivo(dto.getArchivoContenido());
        hecho.setMultimediaNombre(dto.getArchivoNombre());

        return hecho;
    }
}
