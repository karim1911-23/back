package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Remorque;
import dislog.cs.cs.model.utils.GenreRemorque;
import dislog.cs.cs.model.utils.MarqueRemorque;
import dislog.cs.cs.model.utils.ProprieteRemorque;
import dislog.cs.cs.model.utils.TonnageRemorque;
import dislog.cs.cs.repository.RemorqueRepo;

@Service
public class RemorqueService {

    @Autowired
    private RemorqueRepo remorqueRepo;

    // 1. Create
    public Remorque create(Remorque remorque) {
        remorque.setActive(true); // Actif par défaut
        return remorqueRepo.save(remorque);
    }

    // 2. Get all (seulement les actifs)
    public List<Remorque> getAll() {
        return remorqueRepo.findByIsActiveTrue();
    }

    // 3. Get by ID
    public Remorque getById(Long id) {
        return remorqueRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Remorque ID " + id + " introuvable"));
    }

    public Remorque getByMatrcule(String id) {
        return remorqueRepo.findByMatricule(id);
    }

    public Long countActive() {
        return remorqueRepo.countActive();
    }

    // 4. Update
    public Remorque update(Remorque updated) {
        Remorque existing = this.getById(updated.getId());
        existing.setMatricule(updated.getMatricule());
        existing.setMarque(updated.getMarque());
        existing.setGenre(updated.getGenre());
        existing.setTonnage(updated.getTonnage());
        existing.setPropriete(updated.getPropriete());
        return remorqueRepo.save(existing);
    }

    // 5. Soft Delete
    public Remorque delete(Long id) {
        Remorque existing = this.getById(id);
        existing.setActive(false);
        return remorqueRepo.save(existing);
    }

    public List<Remorque> search(String keyword) {
        return remorqueRepo.search(keyword);
    }
}
