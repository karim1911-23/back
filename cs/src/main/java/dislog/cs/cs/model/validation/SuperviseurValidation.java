package dislog.cs.cs.model.validation;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperviseurValidation {
    @NotEmpty(message="Nom est obligatoire")
    private String nom;
    @NotEmpty(message="Prenom est obligatoire")
    private String prenom;
    @NotEmpty(message="Telephone est obligatoire")
    @Length(max=9,message="Numero de Telephone doit contenir 9 chiffre")
    private String telephone;
}
