package domain;

import helpers.EstadoSolicitud;
import usuarios.DatosUsuario;

public class SolicitudAgregado {

    private DatosUsuario datosUsuario;
    private Hecho hecho;
    private EstadoSolicitud estadoSolicitud;

    public SolicitudAgregado(Hecho hecho, DatosUsuario usuario) {
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
        this.datosUsuario = usuario;
        this.hecho = hecho;
    }

    public void aceptar(){
        this.estadoSolicitud = EstadoSolicitud.ACEPTADA;
        //TODO

    }

    public void rechazar() {
        this.estadoSolicitud = EstadoSolicitud.RECHAZADA;
    }
}
