package dislog.cs.cs.model.validation;

import java.util.List;

import dislog.cs.cs.model.Region;
import dislog.cs.cs.model.Vehicule;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientValidation {

    private Long id;
    @NotEmpty(message = "Nom est obligatoire")
    private String nom;
    private String logo;
    @NotEmpty(message = "Telephone est obligatoire")
    private String telephone;
    private String cp;
    @NotEmpty(message = "Ville est obligatoire")
    private String ville;
    @NotEmpty(message = "Email est obligatoire")
    @Email(message = "Email Invalid")
    private String email;
    @NotEmpty(message = "Adresse est obligatoire")
    private String adresse;

    private String typeClient;
    private List<Vehicule> vehicules;
    private List<Region> regions;

}
