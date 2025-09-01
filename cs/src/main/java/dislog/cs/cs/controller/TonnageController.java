package dislog.cs.cs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Tonnage;
import dislog.cs.cs.service.TonnageService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/tonnage")
@RequiredArgsConstructor
public class TonnageController {
    private final TonnageService service;

    @PostMapping("/add")
    public Tonnage add(@RequestBody Tonnage e) {
        return service.add(e);
    }

    @PutMapping("/edit/{id}")
    public Tonnage edit(@PathVariable Long id, @RequestBody Tonnage e) {
        return service.update(id, e);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Tonnage getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public java.util.List<Tonnage> getAll() {
        return service.getAll();
    }
}