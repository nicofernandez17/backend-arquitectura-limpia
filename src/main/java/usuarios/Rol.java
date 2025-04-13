package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;

public interface Rol {
  SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo);
  void aportarHecho(Hecho hecho); // o devolver Hecho, según tu diseño
}
