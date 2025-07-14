package dislog.cs.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.repository.CollaborateurRepo;

@Service
public class CollaborateurService {
    
    @Autowired
    private CollaborateurRepo collaborateurRepo;
    
}
