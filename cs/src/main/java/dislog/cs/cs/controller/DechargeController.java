package dislog.cs.cs.controller;

import dislog.cs.cs.model.Decharge;
import dislog.cs.cs.service.DechargeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/decharge")
public class DechargeController {

    private final DechargeService dechargeService;

    public DechargeController(DechargeService dechargeService) {
        this.dechargeService = dechargeService;
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<Decharge> createDecharge(@RequestBody Decharge decharge, @PathVariable Long id) {
        Decharge savedDecharge = dechargeService.addDecharge(decharge,id);
        return ResponseEntity.ok(savedDecharge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Decharge> getDecharge(@PathVariable Long id) {
        Decharge decharge = dechargeService.getDechargeById(id);
        return ResponseEntity.ok(decharge);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Decharge>> getAllDecharges() {
        List<Decharge> decharges = dechargeService.getAllDecharges();
        return ResponseEntity.ok(decharges);
    }

   /*  @PutMapping("/{id}")
    public ResponseEntity<Decharge> updateDecharge(
            @PathVariable Long id,
            @RequestBody Decharge dechargeDetails) {
        Decharge updatedDecharge = dechargeService.updateDecharge(id, dechargeDetails);
        return ResponseEntity.ok(updatedDecharge);
    }
 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDecharge(@PathVariable Long id) {
        dechargeService.deleteDecharge(id);
        return ResponseEntity.noContent().build();
    }
}