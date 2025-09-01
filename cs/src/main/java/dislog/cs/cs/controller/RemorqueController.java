package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dislog.cs.cs.model.Remorque;
import dislog.cs.cs.model.utils.GenreRemorque;
import dislog.cs.cs.model.utils.MarqueRemorque;
import dislog.cs.cs.model.utils.ProprieteRemorque;
import dislog.cs.cs.model.utils.TonnageRemorque;
import dislog.cs.cs.service.RemorqueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/api/remorque")
public class RemorqueController {

    @Autowired
    private RemorqueService remorqueService;

    // 1. Create
    @PostMapping("/create")
    public ResponseEntity<Remorque> create(@Valid @RequestBody Remorque remorque) {
        return ResponseEntity.ok(remorqueService.create(remorque));
    }

    // 2. Get all actifs
    @GetMapping("/all")
    public ResponseEntity<List<Remorque>> getAll() {
        return ResponseEntity.ok(remorqueService.getAll());
    }

    // 3. Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Remorque> getById(@PathVariable Long id) {
        return ResponseEntity.ok(remorqueService.getById(id));
    }

    // 4. Update
    @PutMapping("/update")
    public ResponseEntity<Remorque> update(@Valid @RequestBody Remorque remorque) {
        return ResponseEntity.ok(remorqueService.update(remorque));
    }

    // 5. Delete (désactivation)
    @DeleteMapping("/{id}")
    public ResponseEntity<Remorque> delete(@PathVariable Long id) {
        return ResponseEntity.ok(remorqueService.delete(id));
    }

    @GetMapping("/search")
    public List<Remorque> search(@RequestParam String keyword) {
        return remorqueService.search(keyword);
    }

    @GetMapping("/count")
    public Long countActiveRemorques() {
        return remorqueService.countActive();
    }
}
