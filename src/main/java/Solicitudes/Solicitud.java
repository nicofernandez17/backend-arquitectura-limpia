package Solicitudes;

import helpers.EstadoSolicitud;

public abstract class Solicitud {

    EstadoSolicitud estado;

    public void agregar() {
        //TODO
        //Agregar esta solicitd TOTALMENTE NUEVA al archivo/DB de solicitudes
        //SOLO actualiza el archivo/DB de solicitudes
        //La logica de cambiar la fuente de datos se hace en cada solicitud
    }
    public void actualizar(){
        //TODO
        //Buscar actualizar la solicitud QUE YA FUE AGERGADA del Archivo/DB.
        //SOLO actualiza el archivo/DB de solicitudes
        //La logica de cambiar la fuente de datos se hace en cada solicitud
    }

    public void aceptar() {
        estado = EstadoSolicitud.ACEPTADA;
        this.actualizar();
    }

    public void rechazar(){
        estado = EstadoSolicitud.RECHAZADA;
        this.actualizar();
    }
}
