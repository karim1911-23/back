package dislog.cs.cs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Decharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Documentation/Accessoires (communs et différences)
    private boolean carteGrise = false;
    private boolean assurance = false;
    private boolean visiteTechnique = false;
    private boolean vignette = false;
    private boolean carnetCirculationTaxeTonnage = false;
    private boolean carnetTachygraphe = false;
    private boolean extincteurs = false;
    private boolean triangleDePanne = false;
    private boolean cricCleRoue = false;
    private boolean manivelle = false;
    private boolean pneuDeSecours = false;
    private boolean posteRadio = false;

    // Éléments spécifiques à la première image (véhicule léger)
    private boolean casque = false;
    private boolean retroviseurAccessoire = false; // Pour distinguer de l'état des rétroviseurs
    private boolean deuxBatteriesRechargeables = false;
    private boolean deuxChargeurs = false;
    private boolean malletteAR = false;

    // Constatation (communs et différences)
    private boolean aucuneFissureDesVitres = false;
    private boolean etatDeLaCabine = false;
    private boolean etatDeLaCaisse = false;
    private boolean etatDesPneus = false;
    private boolean etatDesBatteries = false;
    private boolean etatDesRetroviseurs = false;
    private boolean signalesClignotants = false;
    private boolean feuxArriereFeuxDeStop = false;
    private boolean etatMoteur = false;
    private boolean parChocAvantEtArriere = false;
    private boolean serruresEtVerrouillage = false;
    private boolean fonctionnementDesVitres = false;
    private boolean cableDeSellage = false;
    private boolean etatDhabillage = false;
    private boolean flexibleDeGonflage = false;

    // Éléments spécifiques à la première image
    private boolean etatDeSiege = false;
    private boolean etatDeCalandre = false;
    private boolean etatDeCaisson = false;
    private boolean fonctionnementDesFreins = false;

    // Commentaires et images
    private String commentaire;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String img7;
    private String img8;
    private String img9;
    private String img10;
    private String fonction;
    private String affectation;
    private String machine;
    private String ville;

    @ManyToOne
    private Collaborateur collaborateur;

    @ManyToOne
    private LieuArret lieuArret;

}