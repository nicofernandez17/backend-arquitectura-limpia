package utn.model.dtos;


import org.springframework.web.multipart.MultipartFile;
import utn.model.domain.Hecho;
import utn.model.helpers.Categoria;
import utn.model.helpers.Origen;
import utn.model.helpers.Ubicacion;

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
                .categoria(Optional.ofNullable(hecho.getCategoria())
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
                .created_at(hecho.getFechaDeCarga()) // ya es LocalDateTime
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

        LocalDate fecha = dto.getFecha_hecho() != null ? dto.getFecha_hecho().toLocalDate() : null;

        // Usar la fecha de creación si viene, o asignar fecha actual con hora
        LocalDateTime fechaDeCarga = dto.getCreated_at() != null
                ? dto.getCreated_at()
                : LocalDateTime.now();

        Hecho hecho = new Hecho(dto.getTitulo(),
                dto.getDescripcion(),
                categoria,
                ubicacion,
                fecha,
                fechaDeCarga,
                Origen.API);

        hecho.setMultimediaArchivo(dto.getArchivoContenido());
        hecho.setMultimediaNombre(dto.getArchivoNombre());
        hecho.setUsuarioId(dto.getUsuarioId());

        return hecho;
    }

    public static HechoDTO fromHechoForm (HechoFormDTO hechoFormDTO) {
        MultipartFile archivo = hechoFormDTO.getArchivo();
        byte[] archivoContenido = null;
        String archivoNombre = null;
        if (archivo != null && !archivo.isEmpty() )
        {
            try {
                archivoContenido = archivo.getBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            archivoNombre = archivo.getOriginalFilename();
        }

        return HechoDTO.builder()
                .titulo(hechoFormDTO.getTitulo())
                .descripcion(hechoFormDTO.getDescripcion())
                .categoria(hechoFormDTO.getCategoria())
                .latitud(hechoFormDTO.getLatitud())
                .longitud(hechoFormDTO.getLongitud())
                .fecha_hecho(hechoFormDTO.getFecha())
                .created_at(null) // TODO verificar
                .archivoContenido(archivoContenido)
                .archivoNombre(archivoNombre)
                .usuarioId(null/*TODO completar*/)
                .build();
    }
}
