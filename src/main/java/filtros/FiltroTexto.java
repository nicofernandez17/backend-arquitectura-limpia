package filtros;

import domain.Hecho;
import lombok.Getter;
import lombok.Setter;

public class FiltroTexto implements FiltroHechos{
    @Getter @Setter
    private String texto;

    @Override
    public boolean filtrar(Hecho hecho) {
        if (hecho == null || hecho.getTitulo() == null) {
            return false;
        }
        return hecho.getTitulo().equalsIgnoreCase(texto);
    }
}
