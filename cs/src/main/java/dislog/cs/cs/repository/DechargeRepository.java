package dislog.cs.cs.repository;

import dislog.cs.cs.model.Decharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DechargeRepository extends JpaRepository<Decharge, Long> {
}