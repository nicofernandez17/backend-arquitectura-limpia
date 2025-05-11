package utn.models.dtos;

import utn.models.domain.Hecho;
import utn.models.helpers.Ubicacion;
import utn.models.helpers.Categoria;
import utn.models.helpers.Origen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class HechoMapper {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ISO_LOCAL_DATE;

    // Dominio → DTO (para API)
    public static HechoDTO aDTO(Hecho hecho) {
        // Usar constructor con 6 parámetros para HechoDTO
        return new HechoDTO(
                hecho.getTitulo(),
                hecho.getDescripcion(),
                Optional.ofNullable(hecho.getCategoria())
                        .map(Object::toString)
                        .orElse(null),
                Optional.ofNullable(hecho.getUbicacion())
                        .map(Ubicacion::getLatitud)
                        .orElse(0.0),
                Optional.ofNullable(hecho.getUbicacion())
                        .map(Ubicacion::getLongitud)
                        .orElse(0.0),
                Optional.ofNullable(hecho.getFecha())
                        .map(f -> f.format(FORMATO_FECHA))
                        .orElse(null)
        );
    }

    public static Hecho aDominio(HechoDTO dto) {
        Categoria categoria = null;
        String categoriaStr = dto.getCategoria();

        if (categoriaStr != null && !categoriaStr.isBlank()) {
            categoria = new Categoria(categoriaStr);
        }

        Ubicacion ubicacion = new Ubicacion(dto.getLatitud(), dto.getLongitud());
        LocalDate fecha = LocalDate.parse(dto.getFecha_hecho(), FORMATO_FECHA);

        return new Hecho(
                dto.getTitulo(),
                dto.getDescripcion(),
                categoria,
                ubicacion,
                fecha,
                LocalDate.now(), // fechaDeCarga
                Origen.API
        );
    }
}
