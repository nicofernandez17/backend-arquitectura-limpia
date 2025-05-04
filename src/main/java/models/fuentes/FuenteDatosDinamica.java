package models.fuentes;

import models.domain.Hecho;
import java.util.ArrayList;
import java.util.List;

public class FuenteDatosDinamica implements FuenteDatos {
  private final List<Hecho> hechosDinamicos;

  public FuenteDatosDinamica() {
    this.hechosDinamicos = new ArrayList<>();
  }

  public void agregarHecho(Hecho hecho) {
    hechosDinamicos.add(hecho);
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return new ArrayList<>(hechosDinamicos);
  }
}