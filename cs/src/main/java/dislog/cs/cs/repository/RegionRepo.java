package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dislog.cs.cs.model.Region;

public interface RegionRepo extends JpaRepository<Region, Long> {
    // Additional query methods can be defined here if needed
    
}
