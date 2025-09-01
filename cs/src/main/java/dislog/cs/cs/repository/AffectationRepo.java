package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dislog.cs.cs.model.Affectation;

public interface AffectationRepo extends JpaRepository<Affectation, Long> {

    @Query(value = """
                SELECT TOP 1 * FROM affectation
                WHERE vehicule_id = :id
                  AND active = 1
                  AND type_affectation = :type
                ORDER BY date_affectation DESC, heure_debut DESC
            """, nativeQuery = true)
    Affectation findLastByVehiculeAndTypeSqlServer(
            @Param("id") Long id,
            @Param("type") String type);

    @Query("SELECT a FROM Affectation a WHERE a.collaborateur.id = :id AND a.active = false AND a.typeAffectation = :type")
    Affectation findByCollaborateurIdAndActive(@Param("id") Long id, @Param("type") String type);

    @Query("SELECT a FROM Affectation a WHERE a.collaborateur.id = :id AND a.typeAffectation = :type")
    Affectation findByCollaborateurIdAndType(@Param("id") Long id, @Param("type") String type);

    @Query("SELECT a FROM Affectation a WHERE a.collaborateur.id = :id AND a.typeAffectation = 'passation' AND a.active = true")
    Affectation findCollabId(@Param("id") Long id);

    @Query("SELECT a FROM Affectation a WHERE a.collaborateur.id = :id AND a.typeAffectation = 'passation' AND a.active = false")
    Affectation findCollabIdF(@Param("id") Long id);

    @Query("""
                SELECT a FROM Affectation a
                WHERE a.collaborateur.id = :id
                  AND a.active = true
                  AND a.typeAffectation = :type
                ORDER BY a.dateAffectation DESC, a.heureDebut DESC
            """)
    List<Affectation> findLatestAffectationByCollaborateurIdAndType(
            @Param("id") Long id,
            @Param("type") String type);

    @Query("""
                SELECT a FROM Affectation a
                WHERE a.typeAffectation = :type
                ORDER BY a.dateAffectation DESC, a.heureDebut DESC
            """)
    List<Affectation> findAllAndType(@Param("type") String type);

}
