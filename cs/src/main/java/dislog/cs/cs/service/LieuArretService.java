package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.LieuArret;
import dislog.cs.cs.repository.LieuArretRepo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LieuArretService {
    private final LieuArretRepo lieuArretRepository;

    public LieuArret add(LieuArret entity) {
        entity.setIsActive(true);
        return lieuArretRepository.save(entity);
    }

    public LieuArret update(LieuArret updated) {
        
        return lieuArretRepository.save(updated);
    }

    public void delete(Long id) {
        LieuArret existing = getById(id);
        existing.setIsActive(false);
        lieuArretRepository.save(existing);
    }

    public LieuArret getById(Long id) {
        return lieuArretRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lieu d'arrêt non trouvé"));
    }

    public Long countActive() {
        return lieuArretRepository.countActive();
    }
    
    public List<LieuArret> getAll() {
        return lieuArretRepository.findAll()
                .stream()
                .filter(LieuArret::getIsActive)
                .collect(Collectors.toList());
    }
}
