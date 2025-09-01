package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Superviseur;
import dislog.cs.cs.model.validation.SuperviseurValidation;
import dislog.cs.cs.service.SuperviseurService;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("api/adminuser/superviseur")
public class SuperviseurController {
    @Autowired
    private SuperviseurService superviseurService;

    @PostMapping("/create")
    public ResponseEntity<SuperviseurValidation> create(@RequestBody @Valid SuperviseurValidation sv) {
        SuperviseurValidation sv1 = superviseurService.create(sv);
        return ResponseEntity.status(200).body(sv1);
    }

    @PutMapping("/update")
    public ResponseEntity<SuperviseurValidation> update(@RequestBody @Valid SuperviseurValidation sv) {
        return ResponseEntity.status(200).body(superviseurService.update(sv));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Superviseur>> getAll() {
        List<Superviseur> ses = superviseurService.getAll();
        return ResponseEntity.status(200).body(ses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Superviseur> getById(@PathVariable Long id) {
        Superviseur s = superviseurService.getById(id);
        return ResponseEntity.status(200).body(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Superviseur> delete(@PathVariable Long id) {
        Superviseur s = superviseurService.delete(id);
        return ResponseEntity.status(200).body(s);
    }

    @GetMapping("/count")
    public Long countActiveSuperviseurs() {
        return superviseurService.countActive();
    }
}
