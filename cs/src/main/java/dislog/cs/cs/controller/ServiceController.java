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

import dislog.cs.cs.model.Poste;
import dislog.cs.cs.model.Service;
import dislog.cs.cs.service.ServiceCollaborateurService;

@RestController
@RequestMapping("/api/admin/service")
public class ServiceController {

    @Autowired
    private ServiceCollaborateurService scs;

    @PostMapping("/create")
    public ResponseEntity<Service> create(@RequestBody Service s) {
        return ResponseEntity.status(200).body(scs.create(s));
    }

    @PutMapping("/update")
    public ResponseEntity<Service> update(@RequestBody Service s) {
        return ResponseEntity.status(200).body(scs.create(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Service> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(scs.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Service>> getAll() {
        return ResponseEntity.status(200).body(scs.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(scs.getById(id));
    }

    @GetMapping("/count")
    public Long countActiveServices() {
        return scs.countActive();
    }
}
