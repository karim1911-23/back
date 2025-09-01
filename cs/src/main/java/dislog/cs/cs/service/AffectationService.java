package dislog.cs.cs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Affectation;
import dislog.cs.cs.model.Collaborateur;
import dislog.cs.cs.model.Vehicule;
import dislog.cs.cs.repository.AffectationRepo;
import jakarta.transaction.Transactional;

@Service
public class AffectationService {

    @Autowired
    private AffectationRepo affectationRepository;

    @Autowired
    private PassationService passationService;
    @Autowired
    private DechargeService dechargeService;
    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private CollaborateurService collaborateurService;
    @Autowired
    private VehiculeService vehiculeService;

    @Transactional
    public Affectation saveAffectation(Affectation affectation, Long id, Long idC) {
        if (idC != null) {
            Collaborateur collaborateur = collaborateurService.getById(idC);
            Affectation a = affectationRepository.findByCollaborateurIdAndType(collaborateur.getId(), "passation");
            if (a != null) {
                a.setActive(false);
                affectationRepository.save(a);
            }
        }
        if ("decharge".equalsIgnoreCase(affectation.getTypeAffectation())) {
            System.out.println("+++++++++++++++++++++++++++++++");
            System.out.println("DECHARGE");
            System.out.println("+++++++++++++++++++++++++++++++");
            List<Affectation> result = affectationRepository.findLatestAffectationByCollaborateurIdAndType(
                    affectation.getCollaborateur().getId(), "passation");

            if (!result.isEmpty()) {
                Affectation last = result.get(0); // ✅ la plus récente
                last.setActive(false);
                Long month = getMonth(result.get(0).getDateAffectation(), affectation.getDateAffectation());
                affectation.setPeriode(month.toString());
                affectationRepository.save(last);
            }
            Vehicule v = vehiculeService.getById(affectation.getVehicule().getId());
            v.setActionVehicules("EN ARRET DISPONIBLE");
            vehiculeService.update(v);
            affectation.setDecharge(dechargeService.getDechargeById(id));
        }
        if ("maintenance".equalsIgnoreCase(affectation.getTypeAffectation())) {
            System.out.println("+++++++++++++++++++++++++++++++");
            System.out.println("DECHARGE 1");
            System.out.println("+++++++++++++++++++++++++++++++");
            Collaborateur col = affectation.getCollaborateur();
            Long colId = col == null ? null : col.getId();
            List<Affectation> result = affectationRepository.findLatestAffectationByCollaborateurIdAndType(
                    colId, "passation");

            if (!result.isEmpty()) {
                Affectation last = result.get(0); // ✅ la plus récente
                last.setActive(false);
                affectationRepository.save(last);
            }
            Vehicule v = vehiculeService.getById(affectation.getVehicule().getId());
            v.setActionVehicules("EN ARRET - CHEZ DISTRIBUTEUR");
            vehiculeService.update(v);

            affectation.setMaintenance(maintenanceService.getMaintenanceById(id));
        }
        if ("passation".equalsIgnoreCase(affectation.getTypeAffectation())) {
            System.out.println("+++++++++++++++++++++++++++++++");
            System.out.println("DECHARGE2");
            System.out.println("+++++++++++++++++++++++++++++++");
            Vehicule v = vehiculeService.getById(affectation.getVehicule().getId());
            v.setActionVehicules("EN MARCHE");
            vehiculeService.update(v);

            affectation.setPassation(passationService.getPassationById(id));
        }
        return affectationRepository.save(affectation);
    }

    public List<Affectation> getAllAffectations() {
        return affectationRepository.findAllAndType("passation");
    }

    public List<Affectation> getAllAffectationsD() {
        return affectationRepository.findAllAndType("decharge");
    }

    public List<Affectation> getAllAffectationsM() {
        return affectationRepository.findAllAndType("maintenance");
    }

    public Affectation getAffectationById(Long id) {
        return affectationRepository.findById(id).orElse(null);
    }

    public Affectation getAffectationByVId(Long id) {
        return affectationRepository.findLastByVehiculeAndTypeSqlServer(id, "passation");
    }

    public Affectation getAffectationByCollabId(Long id) {
        return affectationRepository.findCollabId(id);
    }

    public Affectation getAffectationByCollabIdF(Long id) {
        return affectationRepository.findCollabIdF(id);
    }

    public Long getMonth(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Dates de départ et de fin
        LocalDate date1F = LocalDate.parse(date1, formatter);
        LocalDate date2F = LocalDate.parse(date2, formatter);

        // Calculer le nombre de mois
        Long mois = ChronoUnit.MONTHS.between(date1F, date2F);
        return mois;
    }
}
