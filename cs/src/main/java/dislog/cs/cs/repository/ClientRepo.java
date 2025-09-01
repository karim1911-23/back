package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dislog.cs.cs.model.Client;

public interface ClientRepo  extends JpaRepository<Client, Long>{
    @Query("SELECT c FROM Client c WHERE c.isActive=true AND (c.nom LIKE %:query% OR c.telephone LIKE %:query% OR c.cp LIKE %:query% OR c.ville LIKE %:query% OR c.adresse LIKE %:query% OR c.email LIKE %:query%)")
    List<Client> search(String query);

    // Clients de type HABILLAGE
    @Query("SELECT c FROM Client c WHERE c.typeClient = 'habillage' AND c.isActive = true")
    List<Client> findByH();

    // Clients de type clientMaintenace
    @Query("SELECT c FROM Client c WHERE c.typeClient = 'clientMaintenace' AND c.isActive = true")
    List<Client> findByM();

    @Query("SELECT COUNT(c.id) FROM Client c WHERE c.isActive = true")
    Long countActive();

    List<Client> findTop3ByIsActiveTrueOrderByCreatedAtDesc();

}
