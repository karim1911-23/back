package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Activite;
import dislog.cs.cs.model.Departement;

public interface  DepartementRepo extends JpaRepository<Departement, Long> {
      @Query("SELECT d FROM Departement d WHERE d.isActive = ?1")
    List<Departement> findByActive(boolean active);
}
