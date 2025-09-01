package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Poste;
import dislog.cs.cs.repository.PosteRepo;

@Service
public class PosteService {

    @Autowired
    private PosteRepo posteRepo;

    public Poste create(Poste activite) {
        return posteRepo.save(activite);
    }

    public List<Poste> getAll() {
        return posteRepo.findByActive(true);
    }

    public Poste getById(Long id) {
        return posteRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }

    public Poste delete(Long id) {
        Poste activite = this.getById(id);
        activite.setActive(false);
        return posteRepo.save(activite);
    }

    public Poste getByPoste(String poste) {
        return posteRepo.findByPoste(poste);
    }

    public Long countActive() {
        return posteRepo.countActive();
    }
}
