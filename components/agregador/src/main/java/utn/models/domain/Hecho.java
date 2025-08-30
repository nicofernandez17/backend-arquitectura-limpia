package utn.models.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utn.models.helpers.*;


import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Data
@Entity @Table(name = "hecho")
public class Hecho {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id; //TODO - Cambiar a long o Int; No se porque string

  @Enumerated(EnumType.STRING)
  @Column(name = "consenso_nivel", nullable = false)
  private ConsensoNivel consensoNivel = ConsensoNivel.NINGUNO;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private final String descripcion;

  @Column(name = "titulo")
  private String titulo;

  @OneToOne
  @JoinColumn(name = "categoria_id", referencedColumnName = "id")
  private Categoria categoria;

  @OneToOne
  @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
  private Ubicacion ubicacion;

  @Column(name = "fecha", columnDefinition = "DATE")
  private LocalDate fecha;

  @Column(name = "fecha_de_carga", columnDefinition = "DATE")
  private LocalDate fechaDeCarga;

  @Column(name = "updated_at", columnDefinition = "DATE")
  private LocalDate updated_at;

  @Transient // Actualizar a un string con el path al archivo. Como mucho la posibilidad de subir el archivo y guardar el path
              // No vas a guardar un archivo en una base de datos relacional.
  private byte[] multimediaArchivo;

  @Column(name = "multimedia_nombre")
  private String multimediaNombre;

  @Enumerated(EnumType.STRING)
  @Column(name = "origen", nullable = false)
  private Origen origen;

  @Transient // TODO - Completar cuando veamos listas de datos simples
  private final List<String> etiquetas = new ArrayList<>();

  @Column(name = "eliminado")
  private boolean eliminado = false;

  @Transient //TODO - Completar cuando veamos sets
  private final Set<FuenteNombre> fuentes = new HashSet<>();

  //----------------------------------METODOS-----------------------------------------------//

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