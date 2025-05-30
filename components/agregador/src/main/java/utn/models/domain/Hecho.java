package utn.models.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utn.models.helpers.Categoria;
import utn.models.helpers.Origen;
import utn.models.helpers.Ubicacion;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class Hecho {

  public Hecho(String descripcionDelHecho) {
    this.descripcion = descripcionDelHecho;
  }

  private String id;

  @Getter
  private String titulo;
  @Getter
  private final String descripcion;
  @Getter
  private Categoria categoria;
  @Getter
  private Ubicacion ubicacion;
  @Getter
  private LocalDate fecha;
  @Getter
  private LocalDate fechaDeCarga;
  @Getter
  @Setter
  private byte[] multimediaArchivo;
  @Getter
  @Setter
  private String multimediaNombre;
  @Getter
  private Origen origen;
  private List<String> etiquetas;
  @Getter
  private boolean eliminado;

  public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion,
               LocalDate fecha, LocalDate fechaDeCarga, Origen origen) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.fechaDeCarga = fechaDeCarga;
    this.origen = origen;
    this.multimediaArchivo = null;
    this.multimediaNombre = null;
  }

  public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion,
               LocalDate fecha, LocalDate fechaDeCarga, Origen origen, List<String> etiquetas) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.fechaDeCarga = fechaDeCarga;
    this.origen = origen;
    this.etiquetas = new ArrayList<>(etiquetas);
  }

  public void agregarEtiqueta(String etiqueta) {
    if (!etiquetas.contains(etiqueta)) {
      etiquetas.add(etiqueta);
    }
  }



  public List<String> getEtiquetas() {
    return Collections.unmodifiableList(etiquetas);
  }

  public void marcarComoEliminado() {
    this.eliminado = true;
  }

  public boolean puedeAgregarseacoleccion() {
    return !eliminado;
  }
}
