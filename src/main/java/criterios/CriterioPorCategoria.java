package criterios;

import domain.Hecho;
import helpers.Categoria;

public class CriterioPorCategoria implements CriterioDePertenencia {
    private Categoria categoria;

    public CriterioPorCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean cumple(Hecho hecho) {
        return hecho.getCategoria().getNombre().equals(categoria.getNombre());
    }
}
