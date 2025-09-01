package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Poste;

public interface PosteRepo extends JpaRepository<Poste, Long> {
    @Query("SELECT p FROM Poste p WHERE p.isActive = ?1")
    List<Poste> findByActive(boolean active);

    Poste findByPoste(String poste);

    @Query("SELECT COUNT(p.id) FROM Poste p WHERE p.isActive = true")
    Long countActive();
}
