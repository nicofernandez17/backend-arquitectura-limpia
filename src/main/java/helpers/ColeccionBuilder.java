package helpers;

import criterios.CriterioDePertenencia;
import domain.Coleccion;
import domain.Hecho;
import fuentes.FuenteDatos;
import java.util.List;

public class ColeccionBuilder {
  private Coleccion coleccion;

  public ColeccionBuilder iniciarCon(String titulo, String descripcion, FuenteDatos fuente,
                                     List<CriterioDePertenencia> criterios) {
    this.coleccion = new Coleccion(titulo, descripcion, fuente, criterios);
    return this;
  }

  public ColeccionBuilder buildCriterios(List<CriterioDePertenencia> criterios) {
    this.coleccion.getCriteriosDePertenencia().addAll(criterios);
    return this;
  }

  public ColeccionBuilder buildHechos() {
    this.coleccion.cargarColeccion();
    return this;
  }

  public Coleccion build() {
    return this.coleccion;
  }

  // -----------------------

  public ColeccionBuilder agregarCriterio(CriterioDePertenencia criterio) {
    this.coleccion.getCriteriosDePertenencia().add(criterio);
    return this;
  }

  public ColeccionBuilder agregarHecho(Hecho hecho) {
    this.coleccion.getHechos().add(hecho);
    return this;
  }

}
