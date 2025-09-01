package dislog.cs.cs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.LieuArret;
import dislog.cs.cs.service.LieuArretService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/lieu-arret")
@RequiredArgsConstructor
public class LieuArretController {
    private final LieuArretService service;

    @PostMapping("/add")
    public LieuArret add(@RequestBody LieuArret e) {
        return service.add(e);
    }

    @PutMapping("/edit/{id}")
    public LieuArret edit(@RequestBody LieuArret e) {
        return service.update(e);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/count")
    public Long countActiveLieuxArret() {
        return service.countActive();
    }

    @GetMapping("/{id}")
    public LieuArret getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public java.util.List<LieuArret> getAll() {
        return service.getAll();
    }

}