package utn.models.usuarios;

import utn.models.criterios.CriterioDePertenencia;
import utn.models.domain.Coleccion;
import utn.models.fuentes.FuenteDatos;
import utn.models.helpers.ColeccionBuilder;
import java.util.List;
import utn.models.solicitudes.SolicitudEliminacion;

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