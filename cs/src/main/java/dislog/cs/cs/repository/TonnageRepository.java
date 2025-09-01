package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dislog.cs.cs.model.Tonnage;

@Repository
public interface TonnageRepository extends JpaRepository<Tonnage, Long> {
    Tonnage findByTonnage(String shipId);
}
