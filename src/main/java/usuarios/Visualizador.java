package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;

public class Visualizador implements Rol {

  @Override
  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return new SolicitudEliminacion(hecho, motivo);
  }

  @Override
  public void aportarHecho(Hecho hecho) {
    //TODO
    // lógica opcional para registrar aporte anónimo
  }
}