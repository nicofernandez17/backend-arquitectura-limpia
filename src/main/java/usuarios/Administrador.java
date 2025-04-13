package usuarios;

import domain.Hecho;
import domain.SolicitudEliminacion;
import java.util.List;

public class Administrador {

  public SolicitudEliminacion solicitarEliminacion(Hecho hecho, String motivo) {
    return new SolicitudEliminacion(hecho, motivo);
  }

  public void gestionarSolicitudes(List<SolicitudEliminacion> solicitudes) {
    //TODO Implementar
  }

  public void importarCSV() {
    // lógica de carga de datos
  }

  // Constructor, getters/setters
}