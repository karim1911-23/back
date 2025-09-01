package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dislog.cs.cs.model.Habillage;
import dislog.cs.cs.model.Superviseur;

public interface HabillageRepo extends JpaRepository<Habillage, Long> {

        // Add any custom query methods if needed
        List<Habillage> findBySuperviseurId(Long superviseurId);

        // Nouvelle méthode pour récupérer uniquement la colonne matricule
        @Query("SELECT DISTINCT h.matricule FROM Habillage h")
        List<String> findAllMatricules();

        @Query("""
                            SELECT h FROM Habillage h
                            WHERE (:mois IS NULL OR LOWER(h.mois) = LOWER(:mois))
                              OR  (:matricule IS NULL OR LOWER(h.matricule) = LOWER(:matricule))
                              OR  (:superviseur_id IS NULL OR h.superviseur.id = :superviseur_id)
                        """)
        List<Habillage> searchByMoisOrMatriculeOrSuperviseur(
                        @Param("mois") String mois,
                        @Param("matricule") String matricule,
                        @Param("superviseur_id") Long superviseur_id);

}
