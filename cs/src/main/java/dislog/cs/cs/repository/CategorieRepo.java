package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Categorie;

public interface CategorieRepo extends JpaRepository<Categorie, Long> {
    @Query("SELECT c FROM Categorie c WHERE c.isActive = ?1")
    List<Categorie> findByActive(boolean active);

    Categorie findByCategorie(String nom);

    @Query("SELECT COUNT(c.id) FROM Categorie c WHERE c.isActive = true")
    Long countActive();
}
