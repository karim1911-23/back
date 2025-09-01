package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Marque;
import dislog.cs.cs.repository.MarqueRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarqueService {
    private final MarqueRepository marqueRepository;

    public Marque add(Marque entity) {
        entity.setIsActive(true);
        return marqueRepository.save(entity);
    }

    public Marque update(Long id, Marque updated) {
        Marque existing = getById(id);
        existing.setMarque(updated.getMarque());
        return marqueRepository.save(existing);
    }

    public void delete(Long id) {
        Marque existing = getById(id);
        existing.setIsActive(false);
        marqueRepository.save(existing);
    }

    public Marque getById(Long id) {
        return marqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marque non trouvée"));
    }

    public Marque getByMarque(String id) {
        return marqueRepository.findByMarque(id);
    }

    public List<Marque> getAll() {
        return marqueRepository.findAll()
                .stream()
                .filter(Marque::getIsActive)
                .collect(Collectors.toList());
    }

    public Long countActive() {
        return marqueRepository.countActive();
    }
}
