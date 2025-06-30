package utn.models.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utn.models.helpers.*;


import java.time.LocalDate;
import java.util.*;

@Data
public class Hecho {

  private String id;

  private ConsensoNivel consensoNivel = ConsensoNivel.NINGUNO;

  private final String descripcion;
  private String titulo;
  private Categoria categoria;
  private Ubicacion ubicacion;
  private LocalDate fecha;
  private LocalDate fechaDeCarga;
  private byte[] multimediaArchivo;
  private String multimediaNombre;
  private Origen origen;
  private List<String> etiquetas = new ArrayList<>();
  private boolean eliminado = false;

  private Set<FuenteNombre> fuentes = new HashSet<>();


  public Hecho(String descripcionDelHecho) {
    this.descripcion = descripcionDelHecho;
  }

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

  public List<String> getEtiquetas() {
    return Collections.unmodifiableList(etiquetas);
  }

  public void marcarComoEliminado() {
    this.eliminado = true;
  }

  public boolean puedeAgregarseAColeccion() {
    return !eliminado;
  }

  public void agregarFuente(FuenteNombre fuente) {
    fuentes.add(fuente);
  }

  public Set<FuenteNombre> getFuentes() {
    return Collections.unmodifiableSet(fuentes);
  }


}