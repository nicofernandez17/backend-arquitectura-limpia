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
  private LocalDate updated_at;
  private byte[] multimediaArchivo;
  private String multimediaNombre;
  private Origen origen;

  private final List<String> etiquetas = new ArrayList<>();
  private boolean eliminado = false;

  private final Set<FuenteNombre> fuentes = new HashSet<>();

  // Constructor básico
  public Hecho(String descripcionDelHecho) {
    this.descripcion = descripcionDelHecho;
  }

  // Constructor completo sin etiquetas
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

  // Constructor completo con etiquetas
  public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion,
               LocalDate fecha, LocalDate fechaDeCarga, Origen origen, List<String> etiquetas) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.ubicacion = ubicacion;
    this.fecha = fecha;
    this.fechaDeCarga = fechaDeCarga;
    this.origen = origen;
    this.etiquetas.addAll(etiquetas);
  }

  // Agregar una etiqueta (sin repetir)
  public void agregarEtiqueta(String etiqueta) {
    if (etiqueta != null && !etiquetas.contains(etiqueta)) {
      etiquetas.add(etiqueta);
    }
  }

  // Retorna etiquetas como lista inmodificable
  public List<String> getEtiquetas() {
    return Collections.unmodifiableList(etiquetas);
  }

  // Marcar como eliminado
  public void marcarComoEliminado() {
    this.eliminado = true;
  }

  // Validación de elegibilidad
  public boolean puedeAgregarseAColeccion() {
    return !eliminado;
  }

  // Agrega una fuente
  public void agregarFuente(FuenteNombre fuente) {
    if (fuente != null) {
      fuentes.add(fuente);
    }
  }

  // Agrega múltiples fuentes
  public void agregarFuentes(Set<FuenteNombre> nuevasFuentes) {
    if (nuevasFuentes != null) {
      fuentes.addAll(nuevasFuentes);
    }
  }

  // Retorna fuentes como set inmodificable
  public Set<FuenteNombre> getFuentes() {
    return Collections.unmodifiableSet(fuentes);
  }
}