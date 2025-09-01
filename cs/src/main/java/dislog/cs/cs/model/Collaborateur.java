package dislog.cs.cs.model;

import dislog.cs.cs.model.utils.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collaborateur extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricule;

    private String nom;

    private String prenom;

    private String cin;

    private String cnss;

    private String ctr;

    private String motifDepart;
    private String stc;

    private String dateDepart;

    private String dateAnc;

    private String dateEmb;

    private String dateNaissance;

    private Boolean isActive = true;

    @ManyToOne
    private Activite activite;

    @ManyToOne
    private Poste poste;

    @ManyToOne
    private Departement departement;

    @ManyToOne
    private Site site;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private Service service;
}
