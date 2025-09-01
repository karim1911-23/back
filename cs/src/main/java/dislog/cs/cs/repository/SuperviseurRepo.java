package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Superviseur;

public interface SuperviseurRepo extends JpaRepository<Superviseur, Object> {

        @Query("SELECT s FROM Superviseur s WHERE s.id > 1 AND s.isActive = true")
        List<Superviseur> findByIsActiveTrue();

        Superviseur findByEmail(String email);

        @Query("SELECT COUNT(s.id) FROM Superviseur s WHERE s.isActive = true AND s.id>1")
        Long countActive();
}
