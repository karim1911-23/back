package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dislog.cs.cs.model.Collaborateur;
import dislog.cs.cs.model.dto.StatDTO;

public interface CollaborateurRepo extends JpaRepository<Collaborateur, Long> {
    List<Collaborateur> findByIsActiveTrue();

    @Query("""
                SELECT c FROM Collaborateur c
                WHERE c.isActive = true AND (
                    LOWER(c.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.matricule) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.cin) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.cnss) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.ctr) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.poste.poste) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.service.service) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.categorie.categorie) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.departement.departement) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.activite.activite) LIKE LOWER(CONCAT('%', :keyword, '%')) OR
                    LOWER(c.site.site) LIKE LOWER(CONCAT('%', :keyword, '%'))
                )
            """)
    List<Collaborateur> search(@Param("keyword") String keyword);

    @Query("SELECT COUNT(c.id) FROM Collaborateur c WHERE c.isActive = true")
    Long countActive();

    List<Collaborateur> findTop3ByIsActiveTrueOrderByCreatedAtDesc();

    @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(p.poste, COUNT(c)) " +
            "FROM Collaborateur c JOIN c.poste p GROUP BY p.poste")
    List<StatDTO> countByPoste();

    @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(a.activite, COUNT(c)) " +
            "FROM Collaborateur c JOIN c.activite a GROUP BY a.activite")
    List<StatDTO> countByActivite();

    @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(s.site, COUNT(c)) " +
            "FROM Collaborateur c JOIN c.site s GROUP BY s.site")
    List<StatDTO> countBySite();

    @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(sv.service, COUNT(c)) " +
            "FROM Collaborateur c JOIN c.service sv GROUP BY sv.service")
    List<StatDTO> countByService();

    @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(cat.categorie, COUNT(c)) " +
            "FROM Collaborateur c JOIN c.categorie cat GROUP BY cat.categorie")
    List<StatDTO> countByCategorie();

    @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(d.departement, COUNT(c)) " +
            "FROM Collaborateur c JOIN c.departement d GROUP BY d.departement")
    List<StatDTO> countByDepartement();

}
