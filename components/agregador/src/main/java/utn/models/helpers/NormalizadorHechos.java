package utn.models.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import utn.models.domain.Hecho;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class NormalizadorHechos {

    private final Map<String, String> categorias;
    private final Map<String, String> provincias;


    public NormalizadorHechos() {
        ObjectMapper mapper = new ObjectMapper();
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("diccionario.normalizacion.json")){

            DiccionarioNormalizacion dic = mapper.readValue(is, DiccionarioNormalizacion.class);

        this.categorias = dic.getCategorias();
        this.provincias = dic.getProvincias();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Hecho normalizar(Hecho hecho){
        normalizarCategoria(hecho);
        normalizarProvincia(hecho);
        normalizarFecha(hecho);

        return hecho;
    }

    private void normalizarCategoria(Hecho hecho) {

    }

    private void normalizarProvincia(Hecho hecho) {

    }

    private void normalizarFecha(Hecho hecho) {

    }
}
