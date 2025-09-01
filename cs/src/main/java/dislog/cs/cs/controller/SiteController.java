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
import dislog.cs.cs.model.Site;
import dislog.cs.cs.service.SiteService;

@RestController
@RequestMapping("/api/admin/site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @PostMapping("/create")
    public ResponseEntity<Site> create(@RequestBody Site site) {
        return ResponseEntity.status(200).body(siteService.create(site));
    }

    @PutMapping("/update")
    public ResponseEntity<Site> update(@RequestBody Site site) {
        return ResponseEntity.status(200).body(siteService.create(site));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Site> delete(@PathVariable Long id) {
        return ResponseEntity.status(200).body(siteService.delete(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Site>> getAll() {
        return ResponseEntity.status(200).body(siteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Site> getById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(siteService.getById(id));
    }

    @GetMapping("/count")
    public Long countActiveSites() {
        return siteService.countActive();
    }

}
