package utn.models.dtos;

import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;

import java.util.List;
import java.util.stream.Collectors;

public class ColeccionMapper {

    // Convert Coleccion domain object to ColeccionDTO
    public static ColeccionDTO toDTO(Coleccion coleccion) {
        List<HechoDTO> hechoDTOList = coleccion.getHechos().stream()
                .map(HechoMapper::aDTO)  // Assuming you have HechoMapper for mapping Hecho to HechoDTO
                .collect(Collectors.toList());

        return ColeccionDTO.builder()
                .id(Long.parseLong(coleccion.getId()))  // Convert from String to Long
                .titulo(coleccion.getTitulo())
                .hechos(hechoDTOList)
                .build();
    }

    // Convert ColeccionDTO to Coleccion domain object
    public static Coleccion toDomain(ColeccionDTO coleccionDTO) {
        List<Hecho> hechos = coleccionDTO.getHechos().stream()
                .map(HechoMapper::aDominio)  // Assuming you have HechoMapper for mapping HechoDTO to Hecho
                .collect(Collectors.toList());

        Coleccion coleccion = new Coleccion(coleccionDTO.getTitulo(), ""); // Set empty descripcion or adjust accordingly
        coleccion.setId(String.valueOf(coleccionDTO.getId())); // Convert Long to String
        coleccion.setHechos(hechos);

        return coleccion;
    }
}
