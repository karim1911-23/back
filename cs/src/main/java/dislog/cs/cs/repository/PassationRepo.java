package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dislog.cs.cs.model.Passation;

public interface PassationRepo extends  JpaRepository<Passation, Long> {
    
}
