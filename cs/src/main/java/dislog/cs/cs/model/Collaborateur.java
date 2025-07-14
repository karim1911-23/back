package dislog.cs.cs.model;

import java.util.Date;

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
public class Collaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String cnss;
    private String ctr;
    private Date dateDepart;
    private Date dateAnc;
    private Date dateEmb;
    private Date dateNaissance;

    @ManyToOne()
    private Activite activite;

    @ManyToOne()
    private Poste poste;

    @ManyToOne()
    private Departement departement;

    @ManyToOne()
    private Site site;

    @ManyToOne()
    private Categorie categorie;

    @ManyToOne()
    private Service service;

}