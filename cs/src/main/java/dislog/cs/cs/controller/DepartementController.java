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
import dislog.cs.cs.model.Departement;
import dislog.cs.cs.service.DepartementService;

@RestController
@RequestMapping("/api/admin/departement")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @PostMapping("/create")
    public ResponseEntity<Departement> create(@RequestBody Departement departement) {
        return ResponseEntity.status(200).body(departementService.create(departement));
    }

    @PutMapping("/update")
    public ResponseEntity<Departement> update(@RequestBody Departement departement) {
        return ResponseEntity.status(200).body(departementService.create(departement));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Departement> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(departementService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Departement>> getAll() {
        return ResponseEntity.status(200).body(departementService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(departementService.getById(id));
    }

    @GetMapping("/count")
    public Long countActiveDepartements() {
        return departementService.countActive();
    }

}
