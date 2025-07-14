package dislog.cs.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dislog.cs.cs.model.Client;

public interface ClientRepo  extends JpaRepository<Client, Long>{
    
}
