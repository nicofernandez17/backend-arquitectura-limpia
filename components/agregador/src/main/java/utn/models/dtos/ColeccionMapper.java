package utn.models.dtos;

import utn.models.algoritmos.AlgoritmoConsensoResolver;
import utn.models.domain.Coleccion;
import utn.models.domain.Hecho;
import utn.models.helpers.FuenteNombre;

import java.util.*;
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
    public static Coleccion toDomain(
            ColeccionDTO dto,
            AlgoritmoConsensoResolver algoritmoResolver
    ) {
        if (dto == null) return null;

        Coleccion coleccion = new Coleccion();

        if (dto.getId() != null) {
            coleccion.setId(dto.getId());
        }

        coleccion.setTitulo(dto.getTitulo());
        coleccion.setDescripcion(dto.getDescripcion());

        // HECHOS
        Set<Hecho> hechos = Optional.ofNullable(dto.getHechos())
                .orElse(Collections.emptyList())
                .stream()
                .map(HechoMapper::aDominio)
                .collect(Collectors.toSet());
        coleccion.setHechos(hechos);

        // FUENTES
        coleccion.setFuentes(
                mapearFuentes(dto.getFuentes())
        );

        // ALGORITMO (🔥 acá está la clave)
        coleccion.setAlgoritmo(
                algoritmoResolver.resolverPorNombreClase(dto.getAlgoritmo())
        );

        // CRITERIOS
        coleccion.setCriteriosDePertenencia(new ArrayList<>());

        return coleccion;
    }

    public static List<FuenteNombre> mapearFuentes(List<String> fuentesDTO) {
        if (fuentesDTO == null) {
            return new ArrayList<>();
        }

        return fuentesDTO.stream()
                .map(String::toUpperCase)
                .map(FuenteNombre::valueOf)
                .collect(Collectors.toList());
    }



}

