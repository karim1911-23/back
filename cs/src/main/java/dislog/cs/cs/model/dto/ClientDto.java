package dislog.cs.cs.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private String nom;
    private String logo;
    private String telephone;
    private String cp;
    private String ville;
    private String email;
    private String adresse;
}
