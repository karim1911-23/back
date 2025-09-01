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
import dislog.cs.cs.service.ActiviteService;

@RestController
@RequestMapping("/api/admin/activite")
public class ActiviteController{

    @Autowired
    private ActiviteService activiteService;

    @PostMapping("/create")
    public ResponseEntity<Activite> create(@RequestBody Activite activite){
        return ResponseEntity.status(200).body(activiteService.create(activite));
    }

    @PutMapping("/update")
    public ResponseEntity<Activite> update(@RequestBody Activite activite) {
        return ResponseEntity.status(200).body(activiteService.create(activite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Activite> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(activiteService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Activite>> getAll() {
        return ResponseEntity.status(200).body(activiteService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Activite> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(activiteService.getById(id));
    }

    @GetMapping("/count")
    public Long countActiveActivites() {
        return activiteService.countActive();
    }
}