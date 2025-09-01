package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Habillage;
import dislog.cs.cs.model.dto.HabillageDto;
import dislog.cs.cs.service.HabillageService;

@RestController
@RequestMapping("/api/adminuser/habillage")
public class HabillageController {
    @Autowired
    private HabillageService habillageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Add methods to handle Habillage operations, e.g., create, update, delete, get
    // by ID, etc.
    // Example method to create a new Habillage
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Habillage habillage) {
        Habillage h = habillageService.create(habillage);
        messagingTemplate.convertAndSend("/topic/notification", h);
        return ResponseEntity.ok("Habillage created successfully");
    }

    // Example method to get a Habillage by ID
    @GetMapping("/{id}")
    public HabillageDto getById(@PathVariable Long id) {
        return habillageService.getById(id);
    }

    @GetMapping("/super/{id}")
    public List<HabillageDto> getBySuper(@PathVariable Long id) {
        return habillageService.getBySuper(id);
    }

    // Example method to get all Habillages
    @GetMapping("/all")
    public List<HabillageDto> getAll() {
        return habillageService.getAll();
    }

    @GetMapping("/search")
    public List<HabillageDto> search(
            @RequestParam(required = false) String mois,
            @RequestParam(required = false) String matricule, @RequestParam(required = false) Long superviseur_id) {
        return habillageService.search(mois, matricule, superviseur_id);
    }

    @GetMapping("/matricules")
    public List<String> getAllMatricules() {
        return habillageService.getAllMatricules();
    }
}
