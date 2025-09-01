package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Activite;
import dislog.cs.cs.model.Energie;
import dislog.cs.cs.repository.EnergieRepo;
import dislog.cs.cs.service.EnergieService;

@RestController
@RequestMapping("/api/admin/enregie")
public class EnergieController {

    @Autowired
    private EnergieService energieService;

    @PostMapping("/create")
    public ResponseEntity<Energie> create(@RequestBody Energie energie) {
        return ResponseEntity.status(200).body(energieService.create(energie));
    }

    @PutMapping("/update")
    public ResponseEntity<Energie> update(@RequestBody Energie energie) {
        return ResponseEntity.status(200).body(energieService.create(energie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Energie> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(energieService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Energie>> getAll() {
        return ResponseEntity.status(200).body(energieService.getAll());
    }

    @GetMapping("/count")
    public Long countActiveEnergies() {
        return energieService.countActive();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Energie> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(energieService.getById(id));
    }

}
