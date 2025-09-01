package dislog.cs.cs.service;

import dislog.cs.cs.model.Passation;
import dislog.cs.cs.repository.PassationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.BeanUtils;

@Service
public class PassationService {

    private final PassationRepository passationRepository;

    public PassationService(PassationRepository passationRepository) {
        this.passationRepository = passationRepository;
    }

    public Passation addPassation(Passation passation) {
        System.out.println("********************************************");
        System.out.println(passation);
        System.out.println("********************************************");
        return passationRepository.save(passation);
    }

    public Passation updatePassation(Long id, Passation passationDetails) {
        Passation passation = passationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passation not found with id: " + id));

        // Copie toutes les propriétés sauf l'id
        BeanUtils.copyProperties(passationDetails, passation, "id");

        return passationRepository.save(passation);
    }

    public Passation getPassationById(Long id) {
        return passationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passation not found with id: " + id));
    }

    public List<Passation> getAllPassations() {
        return passationRepository.findAll();
    }

    public void deletePassation(Long id) {
        Passation passation = passationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passation not found with id: " + id));
        passationRepository.delete(passation);
    }
}