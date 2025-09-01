package dislog.cs.cs.model.dto;

import java.util.List;

import dislog.cs.cs.model.Region;
import dislog.cs.cs.model.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private Long id;
    private String nom;
    private String logo;
    private String telephone;
    private String cp;
    private String ville;
    private String email;
    private String adresse;
    private List<Vehicule> vehicules;
    private List<Region> regions;
}
