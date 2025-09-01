package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dislog.cs.cs.model.ProprieteCarteGrise;

@Repository
public interface ProprieteCarteGriseRepository extends JpaRepository<ProprieteCarteGrise, Long> {
    ProprieteCarteGrise findByProprieteCarteGrise(String carteGriseId);
}
