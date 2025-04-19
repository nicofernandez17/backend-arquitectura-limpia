package Solicitudes;

import domain.Hecho;
import helpers.EstadoSolicitud;
import usuarios.DatosUsuario;

public class SolicitudAgregado extends Solicitud {

    private DatosUsuario datosUsuario;
    private Hecho hecho;


    public SolicitudAgregado(Hecho hecho, DatosUsuario usuario) {
        this.estado = EstadoSolicitud.PENDIENTE;
        this.datosUsuario = usuario;
        this.hecho = hecho;
    }

    @Override
    public void aceptar(){
        //TODO
        //Que identifique la fuente
        //Que acceda a la base de datos y elimine el hecho
        //TODO
        //Actualizar el ENUM de "Origen" para vincular el hecho con su fuente

        super.aceptar();

    }
}
