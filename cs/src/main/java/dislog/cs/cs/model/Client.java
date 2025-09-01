package dislog.cs.cs.model;

import java.util.List;

import dislog.cs.cs.model.utils.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logo;
    private String nom;
    private String telephone;
    private String cp;
    private String ville;
    private String email;
    private String adresse;
    private String typeClient;
    private boolean isActive = true;

    @OneToMany
    private List<Vehicule> vehicules;

    @OneToMany
    private List<Region> regions;
}

/*
 * private String region;
 * 
 * @Column(columnDefinition = "TEXT")
 * 
 * @Convert(converter = StringListConverter.class)
 * private List<String> regions;
 */
// ex: ["Marrakech", "Safi", "Essaouira"]
