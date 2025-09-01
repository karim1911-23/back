package dislog.cs.cs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Modele;
import dislog.cs.cs.service.ModeleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/modele")
@RequiredArgsConstructor
public class ModeleController {
    private final ModeleService service;

    @PostMapping("/add")
    public Modele add(@RequestBody Modele e) {
        return service.add(e);
    }

    @PutMapping("/edit/{id}")
    public Modele edit(@PathVariable Long id, @RequestBody Modele e) {
        return service.update(id, e);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/count")
    public Long countActiveModeles() {
        return service.countActive();
    }

    @GetMapping("/{id}")
    public Modele getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public java.util.List<Modele> getAll() {
        return service.getAll();
    }

}