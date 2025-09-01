package dislog.cs.cs.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Affectation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeAffectation; // "habillage" ou "passation"
    private String dateAffectation;
    private String kilometres;
    private String periode;
    private boolean active = true;
    private String heureDebut;

    @ManyToOne
    private Vehicule vehicule;

    @ManyToOne
    private Collaborateur collaborateur;
    /*
     * (cascade=CascadeType.PERSIST)
     */

    @OneToOne(cascade = CascadeType.PERSIST)
    private Passation passation;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Maintenance maintenance;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Decharge decharge;
}
