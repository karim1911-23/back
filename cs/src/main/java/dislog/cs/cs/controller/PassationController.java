package dislog.cs.cs.controller;

import dislog.cs.cs.model.Passation;
import dislog.cs.cs.service.PassationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/admin/passation")
public class PassationController {

    private final PassationService passationService;

    public PassationController(PassationService passationService) {
        this.passationService = passationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Passation> addPassation(@RequestBody Passation passation) {
        Passation newPassation = passationService.addPassation(passation);
        return ResponseEntity.ok(newPassation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passation> updatePassation(@PathVariable Long id, @RequestBody Passation passation) {
        Passation updatedPassation = passationService.updatePassation(id, passation);
        return ResponseEntity.ok(updatedPassation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passation> getPassationById(@PathVariable Long id) {
        Passation passation = passationService.getPassationById(id);
        return ResponseEntity.ok(passation);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Passation>> getAllPassations() {
        List<Passation> passations = passationService.getAllPassations();
        return ResponseEntity.ok(passations);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassation(@PathVariable Long id) {
        passationService.deletePassation(id);
        return ResponseEntity.noContent().build();
    }
}