package usuarios;

import criterios.CriterioDePertenencia;
import domain.Coleccion;
import domain.Hecho;
import fuentes.FuenteDatos;
import helpers.ColeccionBuilder;
import java.util.List;
import solicitudes.SolicitudEliminacion;

public class Administrador {

  private ColeccionBuilder builder;

  //Simplificar parametros
  public Coleccion crearColeccion(String titulo, String descripcion, FuenteDatos fuente,
                                  List<CriterioDePertenencia> criterios) {
    builder.iniciarCon(titulo, descripcion, fuente, criterios);
    builder.buildCriterios(criterios);
    builder.buildHechos();
    return builder.build();
  }

  public void aceptarSolicitud(SolicitudEliminacion solicitud) {
    solicitud.aceptar();
  }

  public void rechazarSolicitud(SolicitudEliminacion solicitud) {
    solicitud.rechazar();
  }

  // Constructor, getters/setters
  public void setBuilder(ColeccionBuilder builder) {
    this.builder = builder;
  }
}