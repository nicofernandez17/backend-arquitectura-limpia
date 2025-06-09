package utn.controllers.seeding;

import org.springframework.stereotype.Service;
import utn.models.domain.Coleccion;
import utn.repositories.ColeccionRepository;
import utn.services.AgregadorService;

@Service
public class ColeccionSeeder {


    private final ColeccionRepository coleccionRepository;
    private final AgregadorService agregadorService;


    public ColeccionSeeder(ColeccionRepository coleccionRepository, AgregadorService agregadorService) {
        this.coleccionRepository = coleccionRepository;
        this.agregadorService = agregadorService;
    }


    public void seed() {

        Coleccion coleccion1 = Coleccion.builder()
                .titulo("Actualidad Económica")
                .descripcion("Últimos hechos relevantes en economía")
                .build();

        Coleccion coleccion2 = Coleccion.builder()
                .titulo("Innovación Tecnológica")
                .descripcion("Novedades y avances tecnológicos")
                .build();

        Coleccion coleccion3 = Coleccion.builder()
                .titulo("Tecnología y Sociedad")
                .descripcion("Impacto de la tecnología en la sociedad actual")
                .build();

        coleccionRepository.save(coleccion1);
        coleccionRepository.save(coleccion2);
        coleccionRepository.save(coleccion3);

        agregadorService.obtenerYGuardarHechos();
    }
}