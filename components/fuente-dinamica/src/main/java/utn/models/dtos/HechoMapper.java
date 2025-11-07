package utn.models.dtos;


import org.springframework.web.multipart.MultipartFile;
import utn.models.domain.Hecho;
import utn.models.helpers.Categoria;
import utn.models.helpers.Origen;
import utn.models.helpers.Ubicacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class HechoMapper {

    // Dominio → DTO
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
                        .map(LocalDate::atStartOfDay)
                        .orElse(null))
                .created_at(hecho.getFechaDeCarga())
                .updated_at(hecho.getUpdated_at())
                .multimediaPath(hecho.getMultimediaNombre())
                .usuarioId(hecho.getUsuarioId())
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
        LocalDate fecha = dto.getFecha_hecho() != null ? dto.getFecha_hecho().toLocalDate() : null;
        LocalDateTime fechaDeCarga = dto.getCreated_at() != null ? dto.getCreated_at() : LocalDateTime.now();

        Hecho hecho = new Hecho(dto.getTitulo(),
                dto.getDescripcion(),
                categoria,
                ubicacion,
                fecha,
                fechaDeCarga,
                Origen.API);
System.out.println("Categoría del hecho: " + dto.getCategoria());
        hecho.setMultimediaNombre(dto.getMultimediaPath());
        hecho.setUsuarioId(dto.getUsuarioId());

        return hecho;
    }

    // Actualiza una entidad existente con datos del DTO
    public static void updateDominio(Hecho hecho, HechoDTO dto) {
        if (dto.getTitulo() != null) hecho.setTitulo(dto.getTitulo());
        if (dto.getDescripcion() != null) hecho.setDescripcion(dto.getDescripcion());

        if (dto.getCategoria() != null && !dto.getCategoria().isBlank()) {
            hecho.setCategoria(new Categoria(dto.getCategoria()));
        }

        if (dto.getLatitud() != null && dto.getLongitud() != null) {
            hecho.setUbicacion(new Ubicacion(dto.getLatitud(), dto.getLongitud()));
        }

        if (dto.getFecha_hecho() != null) {
            hecho.setFecha(dto.getFecha_hecho().toLocalDate());
        }

        if (dto.getMultimediaPath() != null) {
            hecho.setMultimediaNombre(dto.getMultimediaPath());
        }

        // Actualizar timestamp de actualización
        hecho.setUpdated_at(LocalDateTime.now());
    }

    // Form DTO → DTO TODO revisar esto, tal vez podemos volarlo porque ya no usamos MultipartFile en el back
    public static HechoDTO fromHechoForm(HechoFormDTO hechoFormDTO) {

        return HechoDTO.builder()
                .titulo(hechoFormDTO.getTitulo())
                .descripcion(hechoFormDTO.getDescripcion())
                .categoria(hechoFormDTO.getCategoria())
                .latitud(hechoFormDTO.getLatitud())
                .longitud(hechoFormDTO.getLongitud())
                .fecha_hecho(hechoFormDTO.getFecha())
                .created_at(null)
                .multimediaPath(hechoFormDTO.getMultimediaPath())
                .usuarioId(null)
                .build();
    }
}
