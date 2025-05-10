package utn.models;

import lombok.Getter;
import utn.services.FuenteService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Coleccion {


  // Getters y setters
  private List<HechoDTO> hechos;
  private List<FuenteService> fuentes;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;

    public Coleccion(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }



  public void agregarHecho(HechoDTO hecho) {

      this.hechos.add(hecho);

  }

  public void actualizarHechos() {
    hechos = fuentes.stream()
            .flatMap(fuente -> fuente.obtenerHechos().stream())
            .collect(Collectors.toList());
  }

  public List<HechoDTO> getHechos() {
    return Collections.unmodifiableList(hechos);
  }

}