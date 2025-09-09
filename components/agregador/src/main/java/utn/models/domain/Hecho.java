package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;
import utn.models.helpers.*;


import java.time.LocalDate;
import java.util.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "hecho")
public class Hecho {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id; //TODO - Cambiar a long o Int; No se porque string

  @Enumerated(EnumType.STRING)
  @Column(name = "consenso_nivel", nullable = false)
  private ConsensoNivel consensoNivel = ConsensoNivel.NINGUNO;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @Column(name = "titulo")
  private String titulo;

  @OneToOne
  @JoinColumn(name = "categoria_id", referencedColumnName = "id")
  private Categoria categoria;

  @Embedded
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

  @ElementCollection
  private final List<String> etiquetas = new ArrayList<>();

  @Column(name = "eliminado")
  private boolean eliminado = false;

  @ElementCollection(targetClass = FuenteNombre.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "hecho_fuentes", joinColumns = @JoinColumn(name = "hecho_id"))
  @Enumerated(EnumType.STRING) // guarda el nombre del enum en la tabla
  @Column(name = "fuente")
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