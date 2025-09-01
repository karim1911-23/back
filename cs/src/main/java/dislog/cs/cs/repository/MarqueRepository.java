package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dislog.cs.cs.model.Marque;

@Repository
public interface MarqueRepository extends JpaRepository<Marque, Long> {
    Marque findByMarque(String nom);

    @Query("SELECT COUNT(m.id) FROM Marque m WHERE m.isActive = true")
    Long countActive();
}
