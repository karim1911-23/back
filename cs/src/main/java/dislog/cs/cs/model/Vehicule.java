package dislog.cs.cs.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matAX;
    private String matricule;
    private String ww;
    private String numeroChasse;
    private Date dmc;
    private String actionVehicule;
    private boolean passation = false;
    private boolean decharge = false;
    private boolean maintenance = false;
    private boolean isActive = false;
}
