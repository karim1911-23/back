package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Site;

public interface SiteRepo extends JpaRepository<Site, Long> {
    @Query("SELECT s FROM Site s WHERE s.isActive = ?1")
    List<Site> findByActive(boolean active);
    
    Site findBySite(String siteId);

    @Query("SELECT COUNT(s.id) FROM Site s WHERE s.isActive = true")
    Long countActive();
}
