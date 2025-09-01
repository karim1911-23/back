package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Carburant;
import dislog.cs.cs.repository.CarburantRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarburantService {
    private final CarburantRepository   carburantRepository;

    public Carburant add(Carburant entity) {
        entity.setIsActive(true);
        return carburantRepository.save(entity);
    }

    public Carburant update(Long id, Carburant updated) {
        Carburant existing = getById(id);
        existing.setCarburant(updated.getCarburant());
        return carburantRepository.save(existing);
    }

    public void delete(Long id) {
        Carburant existing = getById(id);
        existing.setIsActive(false);
        carburantRepository.save(existing);
    }

    public Carburant getById(Long id) {
        return carburantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carburant non trouvé"));
    }

    public List<Carburant> getAll() {
        return carburantRepository.findAll()
                .stream()
                .filter(Carburant::getIsActive)
                .collect(Collectors.toList());
    }
}
