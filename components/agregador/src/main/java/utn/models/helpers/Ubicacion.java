package utn.models.helpers;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class Ubicacion {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  private long id;

  private Double latitud;

  private Double longitud;

  //----------------------------------METODOS-----------------------------------------------//
  public Ubicacion(Double latitud, Double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String clave() {
    return latitud + "," + longitud;
  }

  public Double getLongitud() {
    return longitud;
  }

  public Double getLatitud() {
    return latitud;
  }
}
