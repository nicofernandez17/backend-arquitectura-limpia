package domain;

import helpers.EstadoSolicitud;
import lombok.Getter;

public class SolicitudEliminacion {

    @Getter
    private Hecho hecho;
    @Getter
    private String descripcion;
    @Getter
    private EstadoSolicitud estado;

    public SolicitudEliminacion(Hecho hecho, String motivo) {
        this.hecho = hecho;
        this.descripcion = motivo;
        this.estado = EstadoSolicitud.PENDIENTE; // Estado inicial
    }

    public void aceptar() {
        this.estado = EstadoSolicitud.ACEPTADA;
    }

    public void rechazar() {
        this.estado = EstadoSolicitud.RECHAZADA;
    }
}
