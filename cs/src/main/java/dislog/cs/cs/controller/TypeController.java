package dislog.cs.cs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Type;
import dislog.cs.cs.service.TypeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/type")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService service;

    @PostMapping("/add")
    public Type add(@RequestBody Type e) {
        return service.add(e);
    }

    @PutMapping("/edit/{id}")
    public Type edit(@PathVariable Long id, @RequestBody Type e) {
        return service.update(id, e);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/count")
    public Long countActiveTypes() {
        return service.countActive();
    }

    @GetMapping("/{id}")
    public Type getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public java.util.List<Type> getAll() {
        return service.getAll();
    }

}