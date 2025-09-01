package dislog.cs.cs.repository;

import dislog.cs.cs.model.Passation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassationRepository extends JpaRepository<Passation, Long> {
}