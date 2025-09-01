package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Affectation;
import dislog.cs.cs.service.AffectationService;

@RestController
@RequestMapping("api/admin/affectation")
public class AffectationController {
    @Autowired
    private AffectationService affectationService;

    @PostMapping({ "/add/{id}", "/add/{id}/{idC}" })
    public ResponseEntity<Affectation> addAffectation(@RequestBody Affectation affectation, @PathVariable Long id,
            @PathVariable(required = false) Long idC) {
        return ResponseEntity.ok(affectationService.saveAffectation(affectation, id, idC));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Affectation>> getAllAffectations() {
        return ResponseEntity.ok(affectationService.getAllAffectations());
    }

    @GetMapping("/allD")
    public ResponseEntity<List<Affectation>> getAllAffectationsD() {
        return ResponseEntity.ok(affectationService.getAllAffectationsD());
    }

    @GetMapping("/allM")
    public ResponseEntity<List<Affectation>> getAllAffectationsM() {
        return ResponseEntity.ok(affectationService.getAllAffectationsM());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getAffectationById(id));
    }

    @GetMapping("/collab/{id}")
    public ResponseEntity<Affectation> getAffectationByCId(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getAffectationByCollabId(id));
    }

    @GetMapping("/collabF/{id}")
    public ResponseEntity<Affectation> getAffectationByCIdF(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getAffectationByCollabIdF(id));
    }

    @GetMapping("/v/{id}")
    public ResponseEntity<Affectation> getAffectationByIdV(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getAffectationByVId(id));
    }
}
