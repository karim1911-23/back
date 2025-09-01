package dislog.cs.cs.model.mapping;

import org.springframework.stereotype.Component;

import dislog.cs.cs.model.Superviseur;
import dislog.cs.cs.model.validation.SuperviseurValidation;

@Component
public class SuperviseurMapping {

    public Superviseur superviseurValidationToSuperv(SuperviseurValidation sv) {
        Superviseur superviseur = new Superviseur();
        superviseur.setNom(sv.getNom());
        superviseur.setPrenom(sv.getPrenom());
        superviseur.setTelephone(sv.getTelephone());
        superviseur.setClient(sv.getClient());
        superviseur.setRegions(sv.getRegions());
        superviseur.setVehicules(sv.getVehicules());
        superviseur.setEmail(sv.getEmail());
        return superviseur;
    }

    public SuperviseurValidation supervToSuperviseurValidation(Superviseur superviseur) {
        SuperviseurValidation sv = new SuperviseurValidation();
        sv.setId(superviseur.getId());
        sv.setNom(superviseur.getNom());
        sv.setPrenom(superviseur.getPrenom());
        sv.setTelephone(superviseur.getTelephone());
        sv.setClient(superviseur.getClient());
        sv.setRegions(superviseur.getRegions());
        sv.setVehicules(superviseur.getVehicules());
        sv.setEmail(superviseur.getEmail());
        return sv;

    }

}
