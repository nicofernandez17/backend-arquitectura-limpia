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
    private final ColeccionService coleccionService;


    public ColeccionSeeder(FuenteEstaticaService fuenteEstatica, FuenteDinamicaService fuenteDinamica, FuenteProxyService fuenteProxy, ColeccionRepository coleccionRepository, ColeccionService coleccionService) {
        this.fuenteEstatica = fuenteEstatica;
        this.fuenteDinamica = fuenteDinamica;
        this.fuenteProxy = fuenteProxy;
        this.coleccionRepository = coleccionRepository;
        this.coleccionService = coleccionService;
    }


    public void seed() {

        // Crear colección 1
        Coleccion coleccion1 = new Coleccion("Econo1", "Actualidad Económica", "Últimos hechos relevantes en economía");
        coleccion1.setFuentes(List.of(fuenteDinamica));

        // Crear colección 2
        Coleccion coleccion2 = new Coleccion("Tecno1", "Innovación Tecnológica", "Novedades y avances tecnológicos");
        coleccion2.setFuentes(List.of(fuenteEstatica));

        // Crear colección 3
        Coleccion coleccion3 = new Coleccion("Tecno4", "Tecnología y Sociedad", "Impacto de la tecnología en la sociedad actual");
        coleccion3.setFuentes(List.of(fuenteProxy));



        coleccionRepository.save(coleccion1);
        coleccionRepository.save(coleccion2);
        coleccionRepository.save(coleccion3);

        coleccionService.actualizarHechosDeTodasLasColecciones();

    }
}