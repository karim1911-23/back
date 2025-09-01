package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.LieuArret;

public interface LieuArretRepo extends JpaRepository<LieuArret, Long> {
    @Query("SELECT COUNT(l.id) FROM LieuArret l WHERE l.isActive = true")
    Long countActive();
}
