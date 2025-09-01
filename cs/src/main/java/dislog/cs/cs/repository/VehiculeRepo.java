package dislog.cs.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dislog.cs.cs.model.Vehicule;
import dislog.cs.cs.model.dto.StatDTO;

public interface VehiculeRepo extends JpaRepository<Vehicule, Long> {
        List<Vehicule> findByIsActiveTrue();

        @Query("""
                    SELECT v FROM Vehicule v
                    WHERE (:search IS NULL OR
                           LOWER(v.matricule) LIKE LOWER(CONCAT('%', :search, '%')) OR
                           LOWER(v.matAX) LIKE LOWER(CONCAT('%', :search, '%')) OR
                           LOWER(v.numeroChasse) LIKE LOWER(CONCAT('%', :search, '%')) OR
                           LOWER(v.ww) LIKE LOWER(CONCAT('%', :search, '%')) OR
                           LOWER(CAST(v.typeVehicule AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
                    )AND v.isActive = true
                    AND (:typeVehicule IS NULL OR LOWER(v.typeVehicule) = LOWER(:typeVehicule))
                    AND (:energieId IS NULL OR LOWER(v.energie.energie) = LOWER(:energieId))
                    AND (:marqueId IS NULL OR LOWER(v.marque.marque) = LOWER(:marqueId))
                    AND (:tonnageId IS NULL OR LOWER(v.tonnage.tonnage) = LOWER(:tonnageId))
                    AND (:modeleId IS NULL OR LOWER(v.modele.modele) = LOWER(:modeleId))
                    AND (:proprieteId IS NULL OR LOWER(v.proprieteCarteGrise.proprieteCarteGrise) = LOWER(:proprieteId))
                    AND (:typeId IS NULL OR LOWER(v.type.type) = LOWER(:typeId))
                    AND (:isActive IS NULL OR v.isActive = :isActive)
                """)
        List<Vehicule> searchAdvanced(
                @Param("search") String search,
                @Param("typeVehicule") String typeVehicule,
                @Param("energieId") String energieId,
                @Param("marqueId") String marqueId,
                @Param("tonnageId") String tonnageId,
                @Param("modeleId") String modeleId,
                @Param("proprieteId") String proprieteId,
                @Param("typeId") String typeId,
                @Param("isActive") Boolean isActive);

        @Query("SELECT COUNT(v.id) FROM Vehicule v WHERE v.isActive = true")
        Long countActive();

        @Query("SELECT COUNT(v.id) FROM Vehicule v WHERE v.isActive = true AND v.actionVehicules=:type")
        Long countByType(@Param("type") String type);

        List<Vehicule> findTop3ByIsActiveTrueOrderByCreatedAtDesc();

        @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(e.energie, COUNT(v)) " +
                        "FROM Vehicule v JOIN v.energie e GROUP BY e.energie")
        List<StatDTO> countByEnergie();

        @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(m.marque, COUNT(v)) " +
                        "FROM Vehicule v JOIN v.marque m GROUP BY m.marque")
        List<StatDTO> countByMarque();

        @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(md.modele, COUNT(v)) " +
                        "FROM Vehicule v JOIN v.modele md GROUP BY md.modele")
        List<StatDTO> countByModele();

        @Query("SELECT new dislog.cs.cs.model.dto.StatDTO(t.type, COUNT(v)) " +
                        "FROM Vehicule v JOIN v.type t GROUP BY t.type")
        List<StatDTO> countByType();
}
