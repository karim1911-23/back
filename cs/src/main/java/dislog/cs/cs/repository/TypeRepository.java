package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dislog.cs.cs.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByType(String name);

     @Query("SELECT COUNT(t.id) FROM Type t WHERE t.isActive = true")
    Long countActive();
}
