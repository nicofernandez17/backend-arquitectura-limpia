package utn.models.domain;

import lombok.*;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.criterios.ICriterioDePertenencia;
import utn.models.helpers.ConsensoNivel;
import utn.models.helpers.FuenteNombre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colleccion")
public class Coleccion {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id; //TODO - Cambiar a long o Int; No se porque string

  @Builder.Default
  @OneToMany(mappedBy = "colleccion")
  @JoinColumn(name = "hecho_id", referencedColumnName = "id")
  private List<Hecho> hechos = new ArrayList<>();

  @Column(name = "algoritmo")
  private IAlgoritmoConsenso algoritmo;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  //@OneToMany(mappedBy = "colleccion")
  //@JoinColumn(name = "criterio_id", referencedColumnName = "id")
  @Transient //TODO - Actualizar cuando sepamos como vincular con Interfaces
  private List<ICriterioDePertenencia> criteriosDePertenencia;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(name = "fuente")
  private List<FuenteNombre> fuentes = new ArrayList<>();


  //----------------------------------METODOS-----------------------------------------------//
  public void agregarHecho(Hecho hecho) {
    if (hecho == null || hechos.contains(hecho)) return;

    if (criteriosDePertenencia == null || criteriosDePertenencia.isEmpty() ||
            criteriosDePertenencia.stream().allMatch(criterio -> criterio.cumple(hecho))) {
      this.hechos.add(hecho);
    }
  }

  public void actualizarHechos(List<Hecho> hechos) {
    if (hechos == null) {
      this.hechos = new ArrayList<>();
      return;
    }

    if (criteriosDePertenencia == null || criteriosDePertenencia.isEmpty()) {
      this.hechos = new ArrayList<>(hechos);
    } else {
      this.hechos = hechos.stream()
              .filter(hecho -> criteriosDePertenencia.stream()
                      .allMatch(criterio -> criterio.cumple(hecho)))
              .collect(Collectors.toList());
    }
  }

  public List<Hecho> getHechos() {
    return hechos.stream()
            .filter(hecho -> !hecho.isEliminado())
            .toList();
  }

  public List<Hecho> getHechosFiltradosPorConsenso() {
    // Si no hay algoritmo, no se puede filtrar por consenso → devolver todos los hechos válidos
    if (algoritmo == null) {
      return getHechos(); // ya filtra los eliminados
    }

    // Si hay algoritmo, aplicar filtro por nivel mínimo de consenso
    return hechos.stream()
            .filter(hecho -> !hecho.isEliminado())
            .filter(hecho -> hecho.getConsensoNivel().getPrioridad() >= algoritmo.getNivelQueAplica().getPrioridad())
            .toList();
  }

  public void agregarHechos(List<Hecho> hechos) {
    this.hechos.addAll(hechos);
  }

}
