package dislog.cs.cs.model;

import java.util.Date;

import dislog.cs.cs.model.utils.BaseEntity;
import dislog.cs.cs.model.utils.TypeVehicule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String matAX;
  private String matricule;
  private String ww;
  private String numeroChasse;
  private String dmc;

  private boolean isActive = true;
  private boolean habillage = false;
  private boolean passation = false;

  @Column(name = "typeVehicule")
  private String typeVehicule;
  private String actionVehicules;

  @ManyToOne
  @JoinColumn(name = "propriete_carte_grise_id")
  private ProprieteCarteGrise proprieteCarteGrise;
  
  @ManyToOne
  @JoinColumn(name = "energie_id")
  private Energie energie;

  @ManyToOne
  @JoinColumn(name = "marque_id")
  private Marque marque;

  @ManyToOne
  @JoinColumn(name = "tonnage_id")
  private Tonnage tonnage;

  @ManyToOne
  @JoinColumn(name = "modele_id")
  private Modele modele;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private Type type;

  @ManyToOne
  private Remorque remorque;
  @ManyToOne
  private Client client;
}


/*
 * @Enumerated(EnumType.STRING)
 * 
 * @Column(name = "action_vehicules")
 * private ActionVehicule actionVehicules;
 */

 /*
 * @ManyToOne
 * 
 * @JoinColumn(name = "lieu_arret_id")
 * private LieuArret lieuArret;
 */

 /*
  * private boolean passation = false;
  * private boolean maintenance = false;
  * private boolean isActive = false;
  */

 // Relations ManyToOne
