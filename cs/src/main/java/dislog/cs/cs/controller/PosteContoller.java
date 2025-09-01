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
import dislog.cs.cs.model.Poste;
import dislog.cs.cs.service.PosteService;

@RestController
@RequestMapping("/api/admin/poste")
public class PosteContoller {

    @Autowired
    private PosteService posteService;

    @PostMapping("/create")
    public ResponseEntity<Poste> create(@RequestBody Poste poste) {
        return ResponseEntity.status(200).body(posteService.create(poste));
    }

    @PutMapping("/update")
    public ResponseEntity<Poste> update(@RequestBody Poste poste) {
        return ResponseEntity.status(200).body(posteService.create(poste));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Poste> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(posteService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Poste>> getAll() {
        return ResponseEntity.status(200).body(posteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poste> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(posteService.getById(id));
    }

    @GetMapping("/count")
    public Long countActivePostes() {
        return posteService.countActive();
    }

}
