package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Tonnage;
import dislog.cs.cs.repository.TonnageRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TonnageService {
    private final TonnageRepository tonnageRepository;

    public Tonnage add(Tonnage entity) {
        entity.setIsActive(true);
        return tonnageRepository.save(entity);
    }

    public Tonnage update(Long id, Tonnage updated) {
        Tonnage existing = getById(id);
        existing.setTonnage(updated.getTonnage());
        return tonnageRepository.save(existing);
    }

    public void delete(Long id) {
        Tonnage existing = getById(id);
        existing.setIsActive(false);
        tonnageRepository.save(existing);
    }

    public Tonnage getById(Long id) {
        return tonnageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tonnage non trouvé"));
    }

    public Tonnage getByTonnage(String id) {
        return tonnageRepository.findByTonnage(id);
    }

    public List<Tonnage> getAll() {
        return tonnageRepository.findAll()
                .stream()
                .filter(Tonnage::getIsActive)
                .collect(Collectors.toList());
    }
}
