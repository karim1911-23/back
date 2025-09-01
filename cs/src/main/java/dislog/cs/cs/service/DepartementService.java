package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Departement;
import dislog.cs.cs.repository.DepartementRepo;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepo departementRepo;

    public Departement create(Departement activite) {
        return departementRepo.save(activite);
    }

    public List<Departement> getAll() {
        return departementRepo.findByActive(true);
    }

    public Departement getById(Long id) {
        return departementRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }

    public Departement getByDepartement(String id) {
        return departementRepo.findByDepartement(id);
    }

    public Long countActive() {
        return departementRepo.countActive();
    }
    public Departement delete(Long id) {
        Departement activite = this.getById(id);
        activite.setActive(false);
        return departementRepo.save(activite);
    }
}
