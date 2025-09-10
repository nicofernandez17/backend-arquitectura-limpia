package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;
import utn.converters.AlgoritmoConsensoConverter;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.criterios.ICriterioDePertenencia;
import utn.models.helpers.ConsensoNivel;
import utn.models.helpers.FuenteNombre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coleccion")
public class Coleccion {
  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id; //TODO - Cambiar a long o Int; No se porque string

  @ManyToMany
  @JoinTable(
          name = "coleccion_hechos",
          joinColumns = @JoinColumn(name = "coleccion_id"),
          inverseJoinColumns = @JoinColumn(name = "hecho_id")
  )
  @Builder.Default
  private List<Hecho> hechos = new ArrayList<>();

  @Convert(converter = AlgoritmoConsensoConverter.class)
  private IAlgoritmoConsenso algoritmo;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;


  @OneToMany
  private List<ICriterioDePertenencia> criteriosDePertenencia;

  @ElementCollection(targetClass = FuenteNombre.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "coleccion_fuentes", joinColumns = @JoinColumn(name = "coleccion_id"))
  @Enumerated(EnumType.STRING) // guarda el nombre del enum en la tabla
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
