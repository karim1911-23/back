package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Activite;

public interface ActiviteRepo extends JpaRepository<Activite, Long> {
    @Query("SELECT a FROM Activite a WHERE a.isActive = ?1")
    List<Activite> findByActive(boolean active);

    Activite findByActivite(String nom);
    
    @Query("SELECT COUNT(a.id) FROM Activite a WHERE a.isActive = true")
    Long countActive();
}
