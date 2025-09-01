package dislog.cs.cs.controller;

import dislog.cs.cs.model.Maintenance;
import dislog.cs.cs.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/add/{clientId}/{collaborateurId}/{lieuArretId}")
    public ResponseEntity<Maintenance> createMaintenance(
            @RequestBody Maintenance maintenance,
            @PathVariable Long clientId,
            @PathVariable Long collaborateurId,
            @PathVariable Long lieuArretId) {
        Maintenance savedMaintenance = maintenanceService.createMaintenance(maintenance, clientId, collaborateurId,
                lieuArretId);
        return ResponseEntity.ok(savedMaintenance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenance(@PathVariable Long id) {
        Maintenance maintenance = maintenanceService.getMaintenanceById(id);
        return ResponseEntity.ok(maintenance);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        return ResponseEntity.ok(maintenances);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}