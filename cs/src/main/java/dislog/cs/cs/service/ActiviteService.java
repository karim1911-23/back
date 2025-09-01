package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Activite;
import dislog.cs.cs.repository.ActiviteRepo;

@Service
public class ActiviteService {

    @Autowired
    private ActiviteRepo activiteRepo;

    public Activite create(Activite activite) {
        return activiteRepo.save(activite);
    }

    public List<Activite> getAll() {
        return activiteRepo.findByActive(true);
    }

    public Activite getById(Long id) {
        return activiteRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }
    public Activite getByActivite(String id) {
        return activiteRepo.findByActivite(id);
    }

    public Long countActive() {
        return activiteRepo.countActive();
    }

    public Activite delete(Long id) {
        Activite activite = this.getById(id);
        activite.setActive(false);
        return activiteRepo.save(activite);
    }
}
