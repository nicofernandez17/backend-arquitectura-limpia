package utn.model.domain;

import jakarta.persistence.*;
import lombok.*;
import utn.model.helpers.Categoria;
import utn.model.helpers.Origen;
import utn.model.helpers.Ubicacion;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hecho")
public class Hecho {

  public Hecho(String descripcionDelHecho) {
    this.descripcion = descripcionDelHecho;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private String titulo;

  @Column(columnDefinition = "TEXT")
  private String descripcion;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  private Categoria categoria;

  @Embedded
  private Ubicacion ubicacion;

  private LocalDate fecha;

  private LocalDateTime fechaDeCarga;

  private LocalDateTime updated_at;

  private byte[] multimediaArchivo;

  private String multimediaNombre;

  @Enumerated(EnumType.STRING)
  private Origen origen;

  @ElementCollection
  private List<String> etiquetas;

  private boolean eliminado;

  private String usuarioId;

  public Hecho(String titulo, String descripcion, Categoria categoria, Ubicacion ubicacion,
               LocalDate fecha, LocalDateTime fechaDeCarga, Origen origen) {
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
               LocalDate fecha, LocalDateTime fechaDeCarga, Origen origen, List<String> etiquetas) {
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
