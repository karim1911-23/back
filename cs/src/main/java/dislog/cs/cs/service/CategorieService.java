package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Activite;
import dislog.cs.cs.model.Categorie;
import dislog.cs.cs.repository.CategorieRepo;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepo categorieRepo;

    public Categorie create(Categorie activite) {
        return categorieRepo.save(activite);
    }

    public List<Categorie> getAll() {
        return categorieRepo.findByActive(true);
    }

    public Categorie getById(Long id) {
        return categorieRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }

    public Categorie getByCategorie(String id) {
        return categorieRepo.findByCategorie(id);
    }

    public Categorie delete(Long id) {
        Categorie activite = this.getById(id);
        activite.setActive(false);
        return categorieRepo.save(activite);
    }

    public Long countActive() {
        return categorieRepo.countActive();
    }
}
