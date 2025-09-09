package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.model.domain.Hecho;
import utn.model.dto.HechoDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHechoRepository extends JpaRepository<Hecho, Long> {

}
