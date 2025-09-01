package dislog.cs.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.repository.ServiceRepo;

@Service
public class ServiceCollaborateurService {

    @Autowired
    private ServiceRepo serviceRepo;

    public dislog.cs.cs.model.Service create(dislog.cs.cs.model.Service activite) {
        return serviceRepo.save(activite);
    }

    public List<dislog.cs.cs.model.Service> getAll() {
        return serviceRepo.findByActive(true);
    }

    public dislog.cs.cs.model.Service getById(Long id) {
        return serviceRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Activite not found : " + id));
    }

    public dislog.cs.cs.model.Service getByService(String id) {
        return serviceRepo.findByService(id);
    }

    public dislog.cs.cs.model.Service delete(Long id) {
        dislog.cs.cs.model.Service activite = this.getById(id);
        activite.setActive(false);
        return serviceRepo.save(activite);
    }

    public Long countActive() {
        return serviceRepo.countActive();
    }
}
