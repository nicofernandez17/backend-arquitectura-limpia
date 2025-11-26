package utn.models.domain;

import jakarta.persistence.*;
import lombok.*;
import utn.converters.AlgoritmoConsensoConverter;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.criterios.ICriterioDePertenencia;
import utn.models.helpers.ConsensoNivel;
import utn.models.helpers.FuenteNombre;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coleccion")
public class Coleccion {

  //----------------------------------ATRIBUTOS-----------------------------------------------//
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "coleccion_hechos",
          joinColumns = @JoinColumn(name = "coleccion_id"),
          inverseJoinColumns = @JoinColumn(name = "hecho_id")
  )
  @Builder.Default
  private Set<Hecho> hechos = new HashSet<>();

  @Convert(converter = AlgoritmoConsensoConverter.class)
  private IAlgoritmoConsenso algoritmo;

  @Column(name = "titulo")
  private String titulo;

  @Column(name = "descripcion", columnDefinition = "TEXT")
  private String descripcion;

  @OneToMany(fetch = FetchType.EAGER)
  private List<ICriterioDePertenencia> criteriosDePertenencia;

  @ElementCollection(targetClass = FuenteNombre.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "coleccion_fuentes", joinColumns = @JoinColumn(name = "coleccion_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "fuente")
  private List<FuenteNombre> fuentes = new ArrayList<>();


  //----------------------------------METODOS-----------------------------------------------//

  public void agregarHecho(Hecho hecho) {
    if (hecho == null) return;

    boolean cumpleCriterios =
            criteriosDePertenencia == null ||
                    criteriosDePertenencia.isEmpty() ||
                    criteriosDePertenencia.stream().allMatch(c -> c.cumple(hecho));

    if (cumpleCriterios) {
      hechos.add(hecho); // Set elimina duplicados automáticamente
    }
  }

  public void actualizarHechos(List<Hecho> nuevosHechos) {
    if (nuevosHechos == null) {
      this.hechos = new HashSet<>();
      return;
    }

    if (criteriosDePertenencia == null || criteriosDePertenencia.isEmpty()) {
      this.hechos = new HashSet<>(nuevosHechos);
    } else {
      this.hechos = nuevosHechos.stream()
              .filter(hecho -> criteriosDePertenencia.stream()
                      .allMatch(c -> c.cumple(hecho)))
              .collect(Collectors.toSet());
    }
  }

  /** Devuelve LISTA para no romper compatibilidad externa */
  public List<Hecho> getHechos() {
    return new ArrayList<>(hechos).stream()
            .filter(h -> !h.isEliminado())
            .toList();
  }

  /** Devuelve LISTA para el front / API */
  public List<Hecho> getHechosFiltradosPorConsenso() {
    if (algoritmo == null) {
      return getHechos();
    }

    return hechos.stream()
            .filter(h -> !h.isEliminado())
            .filter(h -> h.getConsensoNivel().getPrioridad() >=
                    algoritmo.getNivelQueAplica().getPrioridad())
            .toList();
  }

  public void agregarHechos(List<Hecho> hechosNuevos) {
    if (hechosNuevos == null) return;
    this.hechos.addAll(hechosNuevos);
  }
}
