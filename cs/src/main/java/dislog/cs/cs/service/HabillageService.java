package dislog.cs.cs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Habillage;
import dislog.cs.cs.model.Superviseur;
import dislog.cs.cs.model.dto.HabillageDto;
import dislog.cs.cs.repository.HabillageRepo;

@Service
public class HabillageService {

    @Autowired
    private HabillageRepo habillageRepo;
    @Autowired
    private SuperviseurService superviseurService;

    // Add methods to handle Habillage operations, e.g., create, update, delete, get
    // by ID, etc.
    // Example method to create a new Habillage
    public Habillage create(Habillage habillage) {
        return habillageRepo.save(habillage);
    }

    // Example method to get a Habillage by ID
    public HabillageDto getById(Long id) {
        Habillage habillage = habillageRepo.findById(id).orElse(null);
        HabillageDto dto = new HabillageDto();
        dto.setId(habillage.getId());
        dto.setRemarque(habillage.getRemarque());
        dto.setMatricule(habillage.getMatricule());
        dto.setRegion(habillage.getRegion());
        dto.setSuperviseur(habillage.getSuperviseur());
        dto.setImg1("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg1());
        dto.setImg2("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg2());
        dto.setImg3("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg3());
        dto.setImg4("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg4());
        dto.setDateCreation(habillage.getDateCreation());
        dto.setMois(habillage.getMois());

        return dto;
    }

    // Example method to get all Habillages
    public List<HabillageDto> getAll() {
        List<Habillage> habillages = habillageRepo.findAll(Sort.by(Sort.Direction.DESC, "dateCreation"));
        return habillages.stream().map(habillage -> {
            HabillageDto dto = new HabillageDto();
            dto.setId(habillage.getId());
            dto.setRemarque(habillage.getRemarque());
            dto.setMatricule(habillage.getMatricule());
            dto.setRegion(habillage.getRegion());
            dto.setSuperviseur(habillage.getSuperviseur());
            dto.setImg1("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg1());
            dto.setImg2("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg2());
            dto.setImg3("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg3());
            dto.setImg4("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg4());
            dto.setDateCreation(habillage.getDateCreation());
            dto.setMois(habillage.getMois());

            return dto;

        }).collect(Collectors.toList());
    }

    public List<HabillageDto> getBySuper(Long id) {
        Superviseur superviseur = superviseurService.getById(id);
        List<Habillage> habillages = habillageRepo.findBySuperviseurId(id);
        return habillages.stream().map(habillage -> {
            HabillageDto dto = new HabillageDto();
            dto.setId(habillage.getId());
            dto.setRemarque(habillage.getRemarque());
            dto.setMatricule(habillage.getMatricule());
            dto.setRegion(habillage.getRegion());
            dto.setSuperviseur(habillage.getSuperviseur());
            dto.setImg1("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg1());
            dto.setImg2("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg2());
            dto.setImg3("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg3());
            dto.setImg4("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg4());
            dto.setDateCreation(habillage.getDateCreation());
            dto.setMois(habillage.getMois());

            return dto;

        }).collect(Collectors.toList());
    }

    public List<HabillageDto> search(String mois, String matricule, Long superviseur_id) {
        List<Habillage> habillages = habillageRepo.searchByMoisOrMatriculeOrSuperviseur(mois, matricule,
                superviseur_id);
        return habillages.stream().map(habillage -> {
            HabillageDto dto = new HabillageDto();
            dto.setId(habillage.getId());
            dto.setRemarque(habillage.getRemarque());
            dto.setMatricule(habillage.getMatricule());
            dto.setRegion(habillage.getRegion());
            dto.setSuperviseur(habillage.getSuperviseur());
            dto.setImg1("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg1());
            dto.setImg2("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg2());
            dto.setImg3("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg3());
            dto.setImg4("http://localhost:8080/api/adminuser/files/image/" + habillage.getImg4());
            dto.setDateCreation(habillage.getDateCreation());
            dto.setMois(habillage.getMois());

            return dto;

        }).collect(Collectors.toList());
    }

    public List<String> getAllMatricules() {
        return habillageRepo.findAllMatricules();
    }
}
