package utn.models.dto;

import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ColeccionMapper {

    // Convert Coleccion domain object to ColeccionDTO
    public static ColeccionDTO toDTO(Coleccion coleccion) {
        List<HechoDTO> hechoDTOList = coleccion.getHechos().stream()
                .map(HechoMapper::aDTO)  // Assuming you have HechoMapper for mapping Hecho to HechoDTO
                .collect(Collectors.toList());

        return ColeccionDTO.builder()
                .id(coleccion.getId())  // Convert from String to Long
                .titulo(coleccion.getTitulo())
                .hechos(hechoDTOList)
                .build();
    }

    // Convert ColeccionDTO to Coleccion domain object

    public static Coleccion toDomain(ColeccionDTO dto) {
        if (dto == null) return null;

        Coleccion coleccion = new Coleccion();

        // Convertir ID de Long a String, si dto.getId() no es null
        if (dto.getId() != null) {
            coleccion.setId(dto.getId().toString());
        }

        coleccion.setTitulo(dto.getTitulo());

        // Mapear la lista de HechoDTO a Hecho
        List<Hecho> hechos = Optional.ofNullable(dto.getHechos())
                .orElse(Collections.emptyList())
                .stream()
                .map(HechoMapper::aDominio)
                .collect(Collectors.toList());

        coleccion.setHechos(hechos);

        return coleccion;
    }

}