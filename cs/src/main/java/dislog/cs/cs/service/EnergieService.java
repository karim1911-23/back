package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Energie;
import dislog.cs.cs.repository.EnergieRepo;

@Service
public class EnergieService {

    @Autowired
    private EnergieRepo energieRepo;

    public Energie create(Energie activite) {
        return energieRepo.save(activite);
    }

    public List<Energie> getAll() {
        return energieRepo.findByActive(true);
    }

    public Energie getById(Long id) {
        return energieRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }

    public Energie getByEnergie(String id) {
        return energieRepo.findByEnergie(id);
    }

    public Energie delete(Long id) {
        Energie activite = this.getById(id);
        activite.setActive(false);
        return energieRepo.save(activite);
    }

    public Long countActive() {
        return energieRepo.countActive();
    }
}
