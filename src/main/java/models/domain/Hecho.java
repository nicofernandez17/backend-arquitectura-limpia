package models.domain;

import models.helpers.Categoria;
import models.helpers.Origen;
import models.helpers.Ubicacion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import models.solicitudes.SolicitudEliminacion;
import models.usuarios.Contribuyente;


public class Hecho {

  public Hecho(String descripcionDelHecho) {
    this.descripcion = descripcionDelHecho;
  }

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
  //@SuppressWarnings("unused")
  //private Contenido contenidoMultimedia;
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

  public SolicitudEliminacion solicitarEliminacion(Contribuyente contribuyente, String motivo) {
    return new SolicitudEliminacion(this,motivo,contribuyente);
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
