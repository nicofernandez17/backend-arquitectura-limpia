package utn.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import utn.models.domain.Hecho;
import utn.models.helpers.Categoria;
import utn.models.helpers.DiccionarioNormalizacion;
import utn.repositories.ICategoriaRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class NormalizadorService {

    private final ICategoriaRepository categoriaRepository;
    private final Map<String, String> categorias;
    private final Map<String, String> provincias;


    public NormalizadorHechos(ICategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
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
        String nombreNormalizado= categorias.getOrDefault(hecho.getCategoria().getNombre(), "CATEGORIA_DESCONOCIDA");

        // Tener en cuenta que la categoria no es un String, sino que es una clase con un String nombre adentro
        // Buscar la categoría por el nombre normalizado
        Optional<Categoria> categoriaExistente = categoriaRepository.findByNombre(nombreNormalizado);

        // Categoria que voy a asignar al hecho
        Categoria categoria;

        if(categoriaExistente.isPresent()){
            // Si existe, la asigno
            categoria = categoriaExistente.get();
        } else {
            // Si no existe, la creo, la guardo y la asigno
            categoria = new Categoria(nombreNormalizado);
            categoriaRepository.save(categoria);
        }

        // Setear la categoria al hecho (Acá es donde realmente se asigna)
        hecho.setCategoria(categoria);
    }

    private void normalizarProvincia(Hecho hecho) {

    }

    private void normalizarFecha(Hecho hecho) {

    }
}
