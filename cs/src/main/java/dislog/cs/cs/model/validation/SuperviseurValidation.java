package dislog.cs.cs.model.validation;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import dislog.cs.cs.model.Client;
import dislog.cs.cs.model.Region;
import dislog.cs.cs.model.Vehicule;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperviseurValidation {
    private Long id;

    @NotEmpty(message = "Nom est obligatoire")
    private String nom;
    @NotEmpty(message = "Prenom est obligatoire")
    private String prenom;
    @NotEmpty(message = "Telephone est obligatoire")
    @Length(max = 10, message = "Numero de Telephone doit contenir 9 chiffre")
    private String telephone;
    private String email;

    private Client client;

    private List<Region> regions;

    private List<Vehicule> vehicules;
}
