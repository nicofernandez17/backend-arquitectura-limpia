package utn.model.dto;

import utn.model.domain.Hecho;
import utn.model.domain.Ubicacion;
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
                .created_at(hecho.getFechaDeCarga())
                .updated_at(hecho.getUpdated_at())
                .archivoContenido(null)
                .archivoNombre(null)
                .build();
    }

    // Solo tengo Dominio -> DTO porque los hechos van de la Fuente al Agregador, y la fuente no recibe HechosDTO que deba convertir

}
