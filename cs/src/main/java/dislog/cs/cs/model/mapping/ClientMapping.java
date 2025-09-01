package dislog.cs.cs.model.mapping;

import org.springframework.stereotype.Component;

import dislog.cs.cs.model.Client;
import dislog.cs.cs.model.validation.ClientValidation;

@Component
public class ClientMapping {

    public Client clientToclientValidation(ClientValidation cv) {
        Client c = new Client();
        c.setNom(cv.getNom());
        c.setEmail(cv.getEmail());
        c.setCp(cv.getCp());
        c.setLogo(cv.getLogo());
        c.setTelephone(cv.getTelephone());
        c.setAdresse(cv.getAdresse());
        c.setVille(cv.getVille());
        c.setRegions(cv.getRegions());
        c.setTypeClient(cv.getTypeClient());
        c.setId(cv.getId());
        c.setVehicules(cv.getVehicules());

        return c;
    }

    public ClientValidation clientValidationToClient(Client c) {
        ClientValidation cv = new ClientValidation();
        cv.setNom(c.getNom());
        cv.setEmail(c.getEmail());
        cv.setCp(c.getCp());
        cv.setLogo(c.getLogo());
        cv.setTelephone(c.getTelephone());
        cv.setAdresse(c.getAdresse());
        cv.setVille(c.getVille());
        cv.setRegions(c.getRegions());
        cv.setTypeClient(c.getTypeClient());
        cv.setId(c.getId());
        cv.setVehicules(c.getVehicules());
        return cv;
    }
}
