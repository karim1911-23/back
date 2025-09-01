package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dislog.cs.cs.model.TagJawaz;
import dislog.cs.cs.repository.TagJawazRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagJawazService {
    private final TagJawazRepository tagJawazRepository;

    public TagJawaz add(TagJawaz entity) {
        entity.setIsActive(true);
        return tagJawazRepository.save(entity);
    }

    public TagJawaz update(Long id, TagJawaz updated) {
        TagJawaz existing = getById(id);
        existing.setTagJawaz(updated.getTagJawaz());
        return tagJawazRepository.save(existing);
    }

    public void delete(Long id) {
        TagJawaz existing = getById(id);
        existing.setIsActive(false);
        tagJawazRepository.save(existing);
    }

    public TagJawaz getById(Long id) {
        return tagJawazRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag Jawaz non trouvé"));
    }

    public List<TagJawaz> getAll() {
        return tagJawazRepository.findAll()
                .stream()
                .filter(TagJawaz::getIsActive)
                .collect(Collectors.toList());
    }
}
