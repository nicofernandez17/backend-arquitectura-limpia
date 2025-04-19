package Solicitudes;

import domain.Hecho;
import helpers.EstadoSolicitud;
import lombok.Getter;

public class SolicitudEliminacion extends Solicitud {

    @Getter
    private Hecho hecho;
    @Getter
    private String descripcion;

    public SolicitudEliminacion(Hecho hecho, String motivo) {
        this.hecho = hecho;
        this.descripcion = motivo;
        this.estado = EstadoSolicitud.PENDIENTE; // Estado inicial
    }

    @Override
    public void aceptar(){
        //TODO
        //Que identifique la fuente
        //Que acceda a la base de datos y elimine el hecho

        super.aceptar();
    }
}
