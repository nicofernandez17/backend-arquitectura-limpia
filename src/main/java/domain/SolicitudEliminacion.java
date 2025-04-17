package domain;

import helpers.EstadoSolicitud;

public class SolicitudEliminacion {

    private Hecho hecho;
    private String descripcion;
    private EstadoSolicitud estado;

    public SolicitudEliminacion(Hecho hecho, String motivo) {
        this.estado = EstadoSolicitud.PENDIENTE;
        this.hecho = hecho;
        this.descripcion = motivo;
    }

    public void aceptar(){
        estado = EstadoSolicitud.ACEPTADA;

    }

    public void rechazar() {
        estado = EstadoSolicitud.RECHAZADA;
    }
}
