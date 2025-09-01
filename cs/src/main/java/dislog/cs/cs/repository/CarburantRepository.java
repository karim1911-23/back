package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dislog.cs.cs.model.Carburant;

@Repository
public interface CarburantRepository extends JpaRepository<Carburant, Long> {
}
