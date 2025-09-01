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

import dislog.cs.cs.model.Categorie;
import dislog.cs.cs.service.CategorieService;

@RestController
@RequestMapping("/api/admin/catego")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    @PostMapping("/create")
    public ResponseEntity<Categorie> create(@RequestBody Categorie categorie) {
        return ResponseEntity.status(200).body(categorieService.create(categorie));
    }

    @PutMapping("/update")
    public ResponseEntity<Categorie> update(@RequestBody Categorie categorie) {
        return ResponseEntity.status(200).body(categorieService.create(categorie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categorie> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(categorieService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categorie>> getAll() {
        return ResponseEntity.status(200).body(categorieService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(categorieService.getById(id));
    }

    @GetMapping("/count")
    public Long countActiveCategories() {
        return categorieService.countActive();
    }

}
