package utn.models.dtos;

import utn.models.domain.SolicitudEliminacion;

public class SolicitudMapper {

    public static SolicitudDTO toDTO(SolicitudEliminacion solicitud) {
        if (solicitud == null) return null;

        return SolicitudDTO.builder()
                .fechaCreacion(solicitud.getFechaCreacion())
                .estado(solicitud.getEstado().name()) // Enum → String
                .hecho(solicitud.getHecho() != null ? solicitud.getHecho().getTitulo() : "Sin título")
                .motivo(solicitud.getMotivo())
                .build();
    }
}