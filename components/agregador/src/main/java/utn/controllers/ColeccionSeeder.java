package utn.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.repositories.ColeccionRepository;
import utn.services.ColeccionService;
import utn.services.fuentes.FuenteDinamicaService;
import utn.services.fuentes.FuenteEstaticaService;
import utn.services.fuentes.FuenteProxyService;

import java.util.List;

@Service
public class ColeccionSeeder {

    private final FuenteEstaticaService fuenteEstatica;
    private final FuenteDinamicaService fuenteDinamica;
    private final FuenteProxyService fuenteProxy;
    private final ColeccionRepository coleccionRepository;


    public ColeccionSeeder(FuenteEstaticaService fuenteEstatica, FuenteDinamicaService fuenteDinamica, FuenteProxyService fuenteProxy, ColeccionRepository coleccionRepository) {
        this.fuenteEstatica = fuenteEstatica;
        this.fuenteDinamica = fuenteDinamica;
        this.fuenteProxy = fuenteProxy;
        this.coleccionRepository = coleccionRepository;
    }


    public void seed() {

        // Crear colección 1
        Coleccion coleccion1 = new Coleccion("Econo1","Economía", "Hechos económicos recientes");
        coleccion1.setFuentes(List.of(fuenteDinamica));

        // Crear colección 2
        Coleccion coleccion2 = new Coleccion("Tecno1","Tecnología", "Innovaciones tecnológicas");
        coleccion2.setFuentes(List.of(fuenteDinamica)); // misma instancia

        // Simulamos que obtenemos los hechos desde las fuentes
        coleccion1.actualizarHechos();
        coleccion2.actualizarHechos();

        coleccionRepository.add(coleccion1);
        coleccionRepository.add(coleccion2);

    }
}