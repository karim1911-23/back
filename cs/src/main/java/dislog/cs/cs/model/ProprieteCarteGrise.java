package dislog.cs.cs.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "propriete_carte_grise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProprieteCarteGrise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proprieteCarteGrise;

    private Boolean isActive = true;
}
