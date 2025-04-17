package usuarios;

import criterios.CriterioDePertenencia;
import domain.Coleccion;
import domain.Hecho;
import domain.SolicitudEliminacion;
import fuentes.FuenteDatos;
import helpers.ColeccionBuilder;

import java.util.List;

public class Administrador {

  private ColeccionBuilder builder;


  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return new SolicitudEliminacion(hecho, motivo);
  }

  //Simplificar parametros
  public Coleccion crearColeccion(String titulo, String descripcion, FuenteDatos fuente, List<CriterioDePertenencia> criterios) {
    builder.iniciarCon(titulo, descripcion, fuente);
    builder.buildCriterios(criterios);
    builder.buildHechos();
    return builder.build();

  }



  // Constructor, getters/setters
  public void setBuilder(ColeccionBuilder builder) {
    this.builder = builder;
  }
}