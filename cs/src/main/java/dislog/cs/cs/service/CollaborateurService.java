package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Collaborateur;
import dislog.cs.cs.model.dto.StatDTO;
import dislog.cs.cs.repository.CollaborateurRepo;

@Service
public class CollaborateurService {

    @Autowired
    private CollaborateurRepo collaborateurRepo;

    // Création ou mise à jour
    public Collaborateur save(Collaborateur collaborateur) {
        return collaborateurRepo.save(collaborateur);
    }

    // Récupérer tous les collaborateurs actifs
    public List<Collaborateur> getAll() {
        return collaborateurRepo.findByIsActiveTrue();
    }

    // Récupérer un collaborateur actif par id
    public Collaborateur getById(Long id) {
        return collaborateurRepo.findById(id)
                .filter(Collaborateur::getIsActive)
                .orElseThrow(() -> new UserNotFoundException("Collaborateur id : " + id + " introuvable"));
    }

    public List<Collaborateur> getLast3Collaborateurs() {
        return collaborateurRepo.findTop3ByIsActiveTrueOrderByCreatedAtDesc();
    }
    // Suppression soft : désactive le collaborateur
    public void delete(Long id) {
        Collaborateur collaborateur = getById(id);
        collaborateur.setIsActive(false);
        collaborateurRepo.save(collaborateur);
    }
    
    public Long countActive() {
        return collaborateurRepo.countActive();
    }
    public List<Collaborateur> searchCollaborateurs(String keyword) {
        return collaborateurRepo.search(keyword);
    }


    public List<StatDTO> getStatsByPoste() {
        return collaborateurRepo.countByPoste();
    }

    public List<StatDTO> getStatsByActivite() {
        return collaborateurRepo.countByActivite();
    }

    public List<StatDTO> getStatsBySite() {
        return collaborateurRepo.countBySite();
    }

    public List<StatDTO> getStatsByService() {
        return collaborateurRepo.countByService();
    }

    public List<StatDTO> getStatsByCategorie() {
        return collaborateurRepo.countByCategorie();
    }

    public List<StatDTO> getStatsByDepartement() {
        return collaborateurRepo.countByDepartement();
    }

}
