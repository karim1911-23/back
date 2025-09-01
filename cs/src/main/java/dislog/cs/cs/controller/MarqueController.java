package dislog.cs.cs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Marque;
import dislog.cs.cs.service.MarqueService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/marque")
@RequiredArgsConstructor
public class MarqueController {
    private final MarqueService service;

    @PostMapping("/add")
    public Marque add(@RequestBody Marque e) {
        return service.add(e);
    }

    @PutMapping("/edit/{id}")
    public Marque edit(@PathVariable Long id, @RequestBody Marque e) {
        return service.update(id, e);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/count")
    public Long countActiveMarques() {
        return service.countActive();
    }
    
    @GetMapping("/{id}")
    public Marque getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public java.util.List<Marque> getAll() {
        return service.getAll();
    }

   
}