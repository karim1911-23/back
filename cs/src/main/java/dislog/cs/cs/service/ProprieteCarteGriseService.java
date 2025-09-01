package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.ProprieteCarteGrise;
import dislog.cs.cs.repository.ProprieteCarteGriseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProprieteCarteGriseService {
    private final ProprieteCarteGriseRepository repository;

    public ProprieteCarteGrise add(ProprieteCarteGrise entity) {
        entity.setIsActive(true);
        return repository.save(entity);
    }

    public ProprieteCarteGrise update(Long id, ProprieteCarteGrise updated) {
        ProprieteCarteGrise existing = getById(id);
        existing.setProprieteCarteGrise(updated.getProprieteCarteGrise());
        return repository.save(existing);
    }

    public void delete(Long id) {
        ProprieteCarteGrise existing = getById(id);
        existing.setIsActive(false);
        repository.save(existing);
    }

    public ProprieteCarteGrise getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriété Carte Grise non trouvée"));
    }

    public ProprieteCarteGrise getByProprieteCarteGrise(String id) {
        return repository.findByProprieteCarteGrise(id);
    }

    public List<ProprieteCarteGrise> getAll() {
        return repository.findAll()
                .stream()
                .filter(ProprieteCarteGrise::getIsActive)
                .collect(Collectors.toList());
    }
}
