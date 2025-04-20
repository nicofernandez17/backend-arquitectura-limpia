package Solicitudes;

import domain.Hecho;
import helpers.EstadoSolicitud;
import java.time.LocalDateTime;
import lombok.Getter;

public class SolicitudEliminacion {
    @Getter
    private EstadoSolicitud estado;
    @Getter
    private Hecho hecho;
    @Getter
    private String motivo;
    @Getter
    private final LocalDateTime fechaCreacion;

    public SolicitudEliminacion(Hecho hecho, String motivo) {
        if (motivo == null || motivo.length() < 20) {
            throw new IllegalArgumentException("El motivo debe tener al menos 20 caracteres.");
        }
        this.hecho = hecho;
        this.motivo = motivo;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public void aceptar() {
        this.estado = EstadoSolicitud.ACEPTADA;
        this.hecho.marcarComoEliminado();
    }


    public void rechazar(){
        estado = EstadoSolicitud.RECHAZADA;
        //Eliminar Fecha
    }
}
