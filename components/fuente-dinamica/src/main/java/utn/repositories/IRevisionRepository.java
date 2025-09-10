package utn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.models.domain.Revision;

@Repository
public interface IRevisionRepository extends JpaRepository<Revision, Long> {
}
