package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Type;
import dislog.cs.cs.repository.TypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public Type add(Type entity) {
        entity.setIsActive(true);
        return typeRepository.save(entity);
    }

    public Type update(Long id, Type updated) {
        Type existing = getById(id);
        existing.setType(updated.getType());
        return typeRepository.save(existing);
    }

    public void delete(Long id) {
        Type existing = getById(id);
        existing.setIsActive(false);
        typeRepository.save(existing);
    }

    public Type getById(Long id) {
        return typeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Type non trouvé"));
    }

    public Type getByType(String id) {
        return typeRepository.findByType(id);
    }

    public Long countActive() {
        return typeRepository.countActive();
    }
    public List<Type> getAll() {
        return typeRepository.findAll()
                .stream()
                .filter(Type::getIsActive)
                .collect(Collectors.toList());
    }
}
