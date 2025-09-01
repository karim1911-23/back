package dislog.cs.cs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Vehicule;
import dislog.cs.cs.model.dto.StatDTO;
import dislog.cs.cs.repository.VehiculeRepo;

@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepo vehiculeRepo;

    public Vehicule create(Vehicule vehicule) {
        return vehiculeRepo.save(vehicule);
    }

    public Long countActiveVehicules() {
        return vehiculeRepo.countActive();
    }

    public Long getByType(String type) {
        return vehiculeRepo.countByType(type);
    }

    public List<Vehicule> getLast3Vehicules() {
        return vehiculeRepo.findTop3ByIsActiveTrueOrderByCreatedAtDesc();
    }

    public Vehicule update(Vehicule vehicule) {
        return vehiculeRepo.save(vehicule);
    }

    public Vehicule getById(Long id) {
        Optional<Vehicule> optional = vehiculeRepo.findById(id);
        return optional.orElse(null);
    }

    public List<Vehicule> getAll() {
        return vehiculeRepo.findByIsActiveTrue();
    }

    public void delete(Long id) {
        Optional<Vehicule> optional = vehiculeRepo.findById(id);
        if (optional.isPresent()) {
            Vehicule vehicule = optional.get();
            vehicule.setActive(false);
            vehiculeRepo.save(vehicule);
        }
    }

    public List<Vehicule> searchVehicules(
            String search,
            String typeVehicule,
            String energieId,
            String marqueId,
            String tonnageId,
            String modeleId,
            String proprieteId,
            String typeId,
            Boolean isActive) {
        return vehiculeRepo.searchAdvanced(
                search,
                typeVehicule,
                energieId,
                marqueId,
                tonnageId,
                modeleId,
                proprieteId,
                typeId,
                isActive);
    }

    public List<StatDTO> getCountByEnergie() {
        return vehiculeRepo.countByEnergie();
    }

    public List<StatDTO> getCountByMarque() {
        return vehiculeRepo.countByMarque();
    }

    public List<StatDTO> getCountByModele() {
        return vehiculeRepo.countByModele();
    }

    public List<StatDTO> getCountByType() {
        return vehiculeRepo.countByType();
    }
}
