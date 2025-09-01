package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Superviseur;
import dislog.cs.cs.model.mapping.SuperviseurMapping;
import dislog.cs.cs.model.validation.SuperviseurValidation;
import dislog.cs.cs.repository.SuperviseurRepo;

@Service
public class SuperviseurService {

    @Autowired
    private SuperviseurRepo superviseurRepo;

    @Autowired
    private SuperviseurMapping superviseurMapping;

    public SuperviseurValidation create(SuperviseurValidation sv) {
        Superviseur s = superviseurMapping.superviseurValidationToSuperv(sv);
        return superviseurMapping.supervToSuperviseurValidation(superviseurRepo.save(s));
    }

    public SuperviseurValidation update(SuperviseurValidation sv) {
        Superviseur existing = superviseurRepo.findById(sv.getId())
                .orElseThrow(() -> new UserNotFoundException("Superviseur id : " + sv.getId() + " introuvable"));

        Superviseur updated = superviseurMapping.superviseurValidationToSuperv(sv);
        updated.setId(existing.getId()); // assure qu'on écrase l'existant
        return superviseurMapping.supervToSuperviseurValidation(superviseurRepo.save(updated));
    }

    public Superviseur getById(Long id) {
        return superviseurRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Superviseur id : " + id + " introuvable"));
    }

    public List<Superviseur> getAll() {
        return superviseurRepo.findByIsActiveTrue();
    }

    public Superviseur delete(Long id) {
        Superviseur s = this.getById(id);
        s.setActive(false);
        superviseurRepo.save(s);
        return s;
    }

    public Long countActive() {
        return superviseurRepo.countActive();
    }
}
