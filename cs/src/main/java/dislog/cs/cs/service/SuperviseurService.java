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

    public Superviseur getById(Long id) {
        return superviseurRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Superviseur id : " + id + " introuvable"));
    }

    public List<Superviseur> getAll(){
        return superviseurRepo.findAll();
    }

    public Superviseur delete(Long id){
        Superviseur s=this.getById(id);
        s.setActive(false);
        superviseurRepo.save(s);
        return s;
    }
}
