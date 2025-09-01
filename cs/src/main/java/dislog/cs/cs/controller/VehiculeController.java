package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Vehicule;
import dislog.cs.cs.model.dto.StatDTO;
import dislog.cs.cs.model.utils.TypeVehicule;
import dislog.cs.cs.service.VehiculeService;

@RestController
@RequestMapping("/api/admin/vehicule")
public class VehiculeController {

    @Autowired
    private VehiculeService vehiculeService;

    @PostMapping("/create")
    public Vehicule create(@RequestBody Vehicule vehicule) {
        return vehiculeService.create(vehicule);
    }

    @PutMapping("/update")
    public Vehicule update(@RequestBody Vehicule vehicule) {
        return vehiculeService.update(vehicule);
    }

    @GetMapping("/{id}")
    public Vehicule getById(@PathVariable Long id) {
        return vehiculeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehiculeService.delete(id);
    }
    
    @GetMapping("/count")
    public Long count() {
        return vehiculeService.countActiveVehicules();
    }

    @GetMapping("/act/{type}")
    public Long count(@PathVariable String type) {
        return vehiculeService.getByType(type);
    }

    @GetMapping("/last3")
    public List<Vehicule> getLast3Vehicules() {
        return vehiculeService.getLast3Vehicules();
    }

    @GetMapping("/all")
    public List<Vehicule> getAll() {
        return vehiculeService.getAll();
    }

   

    @GetMapping("/search")
    public List<Vehicule> search(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String typeVehicule,
            @RequestParam(required = false) String energieId,
            @RequestParam(required = false) String marqueId,
            @RequestParam(required = false) String tonnageId,
            @RequestParam(required = false) String modeleId,
            @RequestParam(required = false) String proprieteId,
            @RequestParam(required = false) String typeId,
            @RequestParam(required = false) Boolean isActive) {
        return vehiculeService.searchVehicules(
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

    @GetMapping("/energie")
    public List<StatDTO> countByEnergie() {
        return vehiculeService.getCountByEnergie();
    }

    @GetMapping("/marque")
    public List<StatDTO> countByMarque() {
        return vehiculeService.getCountByMarque();
    }

    @GetMapping("/modele")
    public List<StatDTO> countByModele() {
        return vehiculeService.getCountByModele();
    }

    @GetMapping("/type")
    public List<StatDTO> countByType() {
        return vehiculeService.getCountByType();
    }
}
