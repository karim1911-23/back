package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dislog.cs.cs.model.Collaborateur;

public interface  CollaborateurRepo extends JpaRepository<Collaborateur, Long>{
    
}
