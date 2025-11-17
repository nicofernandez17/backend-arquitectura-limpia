package utn.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.models.domain.Hecho;
import utn.models.dtos.HechoDTO;
import utn.models.dtos.HechoMapper;
import utn.models.dtos.input.HechoFiltroInput;
import utn.repositories.IHechoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HechoService {

    private final IHechoRepository hechoRepository;

    @Autowired
    public HechoService(IHechoRepository hechoRepository) {
        this.hechoRepository = hechoRepository;
    }

    public List<Hecho> obtenerHechos() {
        return hechoRepository.findAll();
    }

    public Optional<Hecho> obtenerHechoById(Long id) {
        return hechoRepository.findById(id);
    }

    public List<Hecho> buscarPorFiltros(HechoFiltroInput filtro) {

        String categoria = filtro.getCategoria();

        LocalDate desde = filtro.getFechaDesde() != null
                ? filtro.getFechaDesde().toLocalDate()
                : null;

        LocalDate hasta = filtro.getFechaHasta() != null
                ? filtro.getFechaHasta().toLocalDate()
                : null;

        return hechoRepository.filtrar(categoria, desde, hasta);

    }
}
