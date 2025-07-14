package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.exception.UserNotFoundException;
import dislog.cs.cs.model.Client;
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

    public ClientValidation create(ClientValidation cv) {
        Client c = clientMapping.clientToclientValidation(cv);
        return clientMapping.clientValidationToClient(clientRepo.save(c));
    }

    public Client getById(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Client id : " + id + " intouvable"));
    }

    public List<ClientDto> getAll() {
        List<Client> clients = clientRepo.findAll(); // ou ton service client

        return clients.stream().map(client -> {
            ClientDto dto = new ClientDto();
            dto.setNom(client.getNom());
            dto.setEmail(client.getEmail());
            dto.setAdresse(client.getAdresse());
            dto.setCp(client.getCp());
            dto.setVille(client.getVille());
            dto.setTelephone(client.getTelephone());

            // Générer l’URL publique d’accès à l’image
            String imageUrl = "http://localhost:8080/api/files/image/" + client.getLogo();
            dto.setLogo(imageUrl);

            return dto;
        }).collect(Collectors.toList());
    }

    public Client delete(Long id) {
        Client c = this.getById(id);
        c.setActive(false);
        return clientRepo.save(c);
    }
}
