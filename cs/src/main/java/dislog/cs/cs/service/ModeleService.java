package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Modele;
import dislog.cs.cs.repository.ModeleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModeleService {
    private final ModeleRepository modeleRepository;

    public Modele add(Modele entity) {
        entity.setIsActive(true);
        return modeleRepository.save(entity);
    }

    public Modele update(Long id, Modele updated) {
        Modele existing = getById(id);
        existing.setModele(updated.getModele());
        return modeleRepository.save(existing);
    }

    public void delete(Long id) {
        Modele existing = getById(id);
        existing.setIsActive(false);
        modeleRepository.save(existing);
    }

    public Modele getById(Long id) {
        return modeleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modèle non trouvé"));
    }

    public Modele getByModele(String id) {
        return modeleRepository.findByModele(id);
    }

    public Long countActive() {
        return modeleRepository.countActive();
    }

    public List<Modele> getAll() {
        return modeleRepository.findAll()
                .stream()
                .filter(Modele::getIsActive)
                .collect(Collectors.toList());
    }
}
