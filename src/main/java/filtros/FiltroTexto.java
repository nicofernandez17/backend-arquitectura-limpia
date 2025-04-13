package filtros;

import domain.Hecho;

public class FiltroTexto implements FiltroHechos{

    private String texto;

    @Override
    public boolean filtrar(Hecho hecho) {

        //Habria que ver como saber que campo queremos chequear, sino crear una clase por cada atributo filtrable
        //hecho.getTitulo().equalsIgnoreCase(texto);
        return false;
    }
}
