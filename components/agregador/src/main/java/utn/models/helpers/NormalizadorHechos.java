package utn.models.helpers;

import utn.models.domain.Hecho;

public class NormalizadorHechos {

    public NormalizadorHechos() {

    }

    public Hecho normalizar(Hecho hecho){
        normalizarCategoria(hecho);
        normalizarProvincia(hecho);
        normalizarFecha(hecho);

        return hecho;
    }

    private void normalizarCategoria(Hecho hecho) {
        //aca normalizamos
    }

    private void normalizarProvincia(Hecho hecho) {

    }

    private void normalizarFecha(Hecho hecho) {

    }
}
