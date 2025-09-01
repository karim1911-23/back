package dislog.cs.cs.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Maintenance {
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
    @Column(name = "cable_de_scellage") // 🛠 correspond au nom exact de la colonne SQL
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
    // List Of images instead 
    // Create a new images class
    // stock them as list between them ";" img2 ; img2 ; img3 ; img4
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
    private String heureIntervention;
    private Date dateIntervention;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private LieuArret lieuArret;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Collaborateur collaborateur;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Client client;

}