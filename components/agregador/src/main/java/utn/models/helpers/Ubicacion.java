package utn.models.helpers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "ubicacion")
public class Ubicacion {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "latitud")
  private final Double latitud;

  @Column(name = "longitud")
  private final Double longitud;

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
