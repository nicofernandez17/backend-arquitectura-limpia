package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utn.models.domain.Hecho;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IHechoRepository extends JpaRepository<Hecho, Long> {
    Optional<Hecho> findByClaveLogica(String claveLogica);
    Optional<Hecho> findByClaveHash(String claveHash);
    @Query("""
        SELECT h FROM Hecho h
        WHERE (:categoria IS NULL OR h.categoria.nombre = :categoria)
        AND (:desde IS NULL OR h.fechaDeCarga >= :desde)
        AND (:hasta IS NULL OR h.fechaDeCarga <= :hasta)
    """)
    List<Hecho> filtrar(
            @Param("categoria") String categoria,
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta
    );
}
