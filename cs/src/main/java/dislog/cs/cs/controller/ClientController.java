package dislog.cs.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.model.Client;
import dislog.cs.cs.model.dto.ClientDto;
import dislog.cs.cs.model.validation.ClientValidation;
import dislog.cs.cs.service.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/client")
@Validated
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientValidation> create(@Valid @RequestBody ClientValidation sv) {
        ClientValidation sv1 = clientService.create(sv);
        return ResponseEntity.status(200).body(sv1);
    }

    @GetMapping("/count")
    public Long countActiveClients() {
        return clientService.countActive();
    }

    @GetMapping("/last3")
    public List<ClientDto> getLast3Clients() {
        return clientService.getLast3Clients();
    }

    @PutMapping("/update")
    public ResponseEntity<ClientValidation> update(@RequestBody @Valid ClientValidation sv) {
        ClientValidation updated = clientService.update(sv);
        return ResponseEntity.status(200).body(updated);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAll() {
        List<ClientDto> ses = clientService.getAll();
        return ResponseEntity.status(200).body(ses);
    }

    @GetMapping("/allH")
    public ResponseEntity<List<Client>> getAllH() {
        List<Client> ses = clientService.clientH();
        return ResponseEntity.status(200).body(ses);
    }

    @GetMapping("/allM")
    public ResponseEntity<List<Client>> getAllM() {
        List<Client> ses = clientService.clientM();
        return ResponseEntity.status(200).body(ses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        Client s = clientService.getById(id);
        return ResponseEntity.status(200).body(s);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Client> delete(@PathVariable Long id) {
        Client s = clientService.delete(id);
        return ResponseEntity.status(200).body(s);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<Client>> getAllSearch(@PathVariable String query) {
        List<Client> ses = clientService.search(query);
        return ResponseEntity.status(200).body(ses);
    }
}
