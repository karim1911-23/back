package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Service;

public interface ServiceRepo extends JpaRepository<Service, Long> {
    @Query("SELECT s FROM Service s WHERE s.isActive = ?1")
    List<Service> findByActive(boolean active);

    Service findByService(String nom);

    @Query("SELECT COUNT(s.id) FROM Service s WHERE s.isActive = true")
    Long countActive();
}
