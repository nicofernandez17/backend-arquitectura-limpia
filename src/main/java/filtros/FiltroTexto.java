package filtros;

import domain.Hecho;

public class FiltroTexto implements FiltroHechos{

    private String texto;

    @Override
    public boolean filtrar(Hecho hecho) {
        if (hecho == null || hecho.getTitulo() == null) {
            return false;
        }
        return hecho.getTitulo().equalsIgnoreCase(texto);
    }
}
