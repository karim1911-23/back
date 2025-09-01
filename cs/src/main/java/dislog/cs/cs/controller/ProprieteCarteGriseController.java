package dislog.cs.cs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.ProprieteCarteGrise;
import dislog.cs.cs.service.ProprieteCarteGriseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/propriete-carte-grise")
@RequiredArgsConstructor
public class ProprieteCarteGriseController {
    private final ProprieteCarteGriseService service;

    @PostMapping("/add")
    public ProprieteCarteGrise add(@RequestBody ProprieteCarteGrise e) {
        return service.add(e);
    }

    @PutMapping("/edit/{id}")
    public ProprieteCarteGrise edit(@PathVariable Long id, @RequestBody ProprieteCarteGrise e) {
        return service.update(id, e);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ProprieteCarteGrise getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public java.util.List<ProprieteCarteGrise> getAll() {
        return service.getAll();
    }
}