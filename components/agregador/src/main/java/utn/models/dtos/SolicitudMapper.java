package utn.models.dtos;

import utn.models.domain.Hecho;
import utn.models.domain.SolicitudEliminacion;
import utn.models.helpers.EstadoSolicitud;

import java.util.Optional;

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

    public static SolicitudEliminacion toDomain(SolicitudDTO dto, Hecho hecho) {
        if (dto == null) return null;

        // Usa el constructor con validación de motivo
        SolicitudEliminacion solicitud = new SolicitudEliminacion(hecho, dto.getMotivo());

        // Si viene fecha o estado desde el DTO (por ejemplo, restauración o testing)
        if (dto.getFechaCreacion() != null) {
            solicitud.setFechaCreacion(dto.getFechaCreacion());
        }

        if (dto.getEstado() != null) {
            try {
                solicitud.setEstado(EstadoSolicitud.valueOf(dto.getEstado()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Estado inválido: " + dto.getEstado(), e);
            }
        }

        return solicitud;
    }

}