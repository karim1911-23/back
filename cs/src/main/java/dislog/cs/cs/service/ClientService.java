package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Client;
import dislog.cs.cs.model.Region;
import dislog.cs.cs.model.Vehicule;
import dislog.cs.cs.model.dto.ClientDto;
import dislog.cs.cs.model.mapping.ClientMapping;
import dislog.cs.cs.model.validation.ClientValidation;
import dislog.cs.cs.repository.ClientRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ClientMapping clientMapping;
    @Autowired
    private VehiculeService vehiculeService;

    public ClientValidation create(ClientValidation cv) {
        Client c = clientMapping.clientToclientValidation(cv);

        // Vérifie si la liste de véhicules est non nulle ET non vide
        if (cv.getVehicules() != null && !cv.getVehicules().isEmpty()) {
            for (Vehicule v : cv.getVehicules()) {
                Vehicule vs = vehiculeService.getById(v.getId());
                vs.setHabillage(true);
                vehiculeService.update(vs);
            }
            c.setTypeClient("habillage"); // affecte à l'entité persistée
        } else {
            c.setTypeClient("clientMaintenace");
        }

        return clientMapping.clientValidationToClient(clientRepo.save(c));
    }

    public Client getById(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Client id : " + id + " intouvable"));
    }

    public ClientValidation update(ClientValidation updatedClient) {
        Client existing = clientRepo.findById(updatedClient.getId())
                .orElseThrow(() -> new UserNotFoundException("Client id : " + updatedClient.getId() + " intouvable"));

        // Mettre à jour les champs
        existing.setNom(updatedClient.getNom());
        existing.setEmail(updatedClient.getEmail());
        existing.setAdresse(updatedClient.getAdresse());
        existing.setCp(updatedClient.getCp());
        existing.setVille(updatedClient.getVille());
        existing.setTelephone(updatedClient.getTelephone());
        existing.setLogo(updatedClient.getLogo()); // image (nom du fichier)
        existing.setRegions(updatedClient.getRegions());
        existing.setVehicules(updatedClient.getVehicules());
        return clientMapping.clientValidationToClient(clientRepo.save(existing));
    }

    public List<ClientDto> getAll() {
        List<Client> clients = clientRepo.findAll(); // récupère tous les clients

        return clients.stream()
                .filter(Client::isActive) // NE GARDE QUE les clients actifs
                .map(client -> {
                    ClientDto dto = new ClientDto();
                    dto.setId(client.getId());
                    dto.setNom(client.getNom());
                    dto.setEmail(client.getEmail());
                    dto.setAdresse(client.getAdresse());
                    dto.setCp(client.getCp());
                    dto.setVille(client.getVille());
                    dto.setTelephone(client.getTelephone());
                    dto.setRegions((List<Region>) client.getRegions());
                    dto.setVehicules((List<Vehicule>) client.getVehicules());

                    // Générer l’URL publique d’accès à l’image
                    String imageUrl = "http://localhost:8080/api/adminuser/files/image/" + client.getLogo();
                    dto.setLogo(imageUrl);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Long countActive() {
        return clientRepo.countActive();
    }

    public List<ClientDto> getLast3Clients() {
        List<Client> clients = clientRepo.findTop3ByIsActiveTrueOrderByCreatedAtDesc();
        return clients.stream()
                .filter(Client::isActive) // NE GARDE QUE les clients actifs
                .map(client -> {
                    ClientDto dto = new ClientDto();
                    dto.setId(client.getId());
                    dto.setNom(client.getNom());
                    // Générer l’URL publique d’accès à l’image
                    String imageUrl = "http://localhost:8080/api/adminuser/files/image/" + client.getLogo();
                    dto.setLogo(imageUrl);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Client delete(Long id) {
        Client c = this.getById(id);
        c.setActive(false);
        return clientRepo.save(c);
    }

    public List<Client> search(String query) {
        return clientRepo.search(query);
    }

    public List<Client> clientH() {
        return clientRepo.findByH();
    }

    public List<Client> clientM() {
        return clientRepo.findByM();
    }

}
