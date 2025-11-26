package utn.models.dtos;

import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ColeccionMapper {

    // DOMAIN → DTO  (Set → List)
    public static ColeccionDTO toDTO(Coleccion coleccion) {
        List<HechoDTO> hechoDTOList = coleccion.getHechos().stream()
                .map(HechoMapper::aDTO)
                .collect(Collectors.toList());

        return ColeccionDTO.builder()
                .id(coleccion.getId())
                .titulo(coleccion.getTitulo())
                .descripcion(coleccion.getDescripcion())
                .hechos(hechoDTOList)
                .build();
    }


    // DTO → DOMAIN  (List → Set)
    public static Coleccion toDomain(ColeccionDTO dto) {
        if (dto == null) return null;

        Coleccion coleccion = new Coleccion();

        if (dto.getId() != null) {
            coleccion.setId(dto.getId());
        }

        coleccion.setTitulo(dto.getTitulo());
        coleccion.setDescripcion(dto.getDescripcion());

        // Convertir List → Set (SIN DUPLICADOS)
        Set<Hecho> hechos = Optional.ofNullable(dto.getHechos())
                .orElse(Collections.emptyList())
                .stream()
                .map(HechoMapper::aDominio)
                .collect(Collectors.toSet());

        coleccion.setHechos(hechos);

        return coleccion;
    }

}

