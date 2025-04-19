package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;

public class Contribuyente extends Usuario {

  @Override
  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return new SolicitudEliminacion(hecho, motivo);
  }

  @Override
  public void aportarHecho(Hecho hecho) {
    //TODO
    // lógica para persistir o validar el hecho
  }
}