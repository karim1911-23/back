package dislog.cs.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dislog.cs.cs.model.Client;
import dislog.cs.cs.model.Collaborateur;
import dislog.cs.cs.model.LieuArret;
import dislog.cs.cs.model.Maintenance;
import dislog.cs.cs.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final ClientService clientService;
    private final CollaborateurService collaborateurService;
    private final LieuArretService lieuArretService;

    @Transactional
    public Maintenance createMaintenance(Maintenance maintenance, Long clientId, Long collaborateurId,
            Long lieuArretId) {
        // Validate input IDs
        if (clientId == null || collaborateurId == null || lieuArretId == null) {
            throw new IllegalArgumentException("Client ID, Collaborateur ID, and LieuArret ID must not be null");
        }

        // Fetch entities
        Client client = clientService.getById(clientId);
        Collaborateur collaborateur = collaborateurService.getById(collaborateurId);
        LieuArret lieuArret = lieuArretService.getById(lieuArretId);

        // Set relationships
        maintenance.setClient(client);
        maintenance.setCollaborateur(collaborateur);
        maintenance.setLieuArret(lieuArret);

        // Save and return the maintenance entity
        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);
        return savedMaintenance;
    }

    public Maintenance getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
    }

    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    public void deleteMaintenance(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
        maintenanceRepository.delete(maintenance);
    }
}