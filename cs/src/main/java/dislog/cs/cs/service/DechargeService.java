package dislog.cs.cs.service;

import dislog.cs.cs.model.Decharge;
import dislog.cs.cs.repository.DechargeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import dislog.cs.cs.model.Affectation;
import dislog.cs.cs.repository.AffectationRepo;

@Service
public class DechargeService {

    private final DechargeRepository dechargeRepository;
    private final CollaborateurService collaborateurService;

    public DechargeService(DechargeRepository dechargeRepository, AffectationRepo affectationRepo,
            CollaborateurService collaborateurService) {
        this.dechargeRepository = dechargeRepository;
        this.collaborateurService=collaborateurService;
    }

    public Decharge addDecharge(Decharge decharge, Long id) {
        decharge.setCollaborateur(collaborateurService.getById(id));

        return dechargeRepository.save(decharge);
    }

    /*
     * public Decharge updateDecharge(Long id, Decharge dechargeDetails) {
     * Decharge decharge = dechargeRepository.findById(id)
     * .orElseThrow(() -> new RuntimeException("Decharge not found with id: " +
     * id));
     * 
     * // Mise à jour des champs documents
     * decharge.setCarteGrise(dechargeDetails.isCarteGrise());
     * decharge.setAssurance(dechargeDetails.isAssurance());
     * decharge.setVisiteTechnique(dechargeDetails.isVisiteTechnique());
     * decharge.setVignette(dechargeDetails.isVignette());
     * decharge.setCarnetCirculationTaxeTonnage(dechargeDetails.
     * isCarnetCirculationTaxeTonnage());
     * decharge.setCarnetTachygraphe(dechargeDetails.isCarnetTachygraphe());
     * 
     * // Mise à jour des champs équipements
     * decharge.setExtincteurs(dechargeDetails.isExtincteurs());
     * decharge.setTriangleDePanne(dechargeDetails.isTriangleDePanne());
     * decharge.setCricCleRoue(dechargeDetails.isCricCleRoue());
     * decharge.setManivelle(dechargeDetails.isManivelle());
     * decharge.setPneuDeSecours(dechargeDetails.isPneuDeSecours());
     * decharge.setPosteRadio(dechargeDetails.isPosteRadio());
     * 
     * // Mise à jour des champs état du véhicule
     * decharge.setAucuneFissureDesVitres(dechargeDetails.isAucuneFissureDesVitres()
     * );
     * decharge.setEtatDeLaCabine(dechargeDetails.isEtatDeLaCabine());
     * decharge.setEtatDeLaCaisse(dechargeDetails.isEtatDeLaCaisse());
     * decharge.setEtatDesPneus(dechargeDetails.isEtatDesPneus());
     * decharge.setEtatDesBatteries(dechargeDetails.isEtatDesBatteries());
     * decharge.setEtatDesRetroviseurs(dechargeDetails.isEtatDesRetroviseurs());
     * decharge.setSignalesClignotants(dechargeDetails.isSignalesClignotants());
     * decharge.setFeuxArriereFeuxDeStop(dechargeDetails.isFeuxArriereFeuxDeStop());
     * decharge.setEtatMoteur(dechargeDetails.isEtatMoteur());
     * decharge.setParChocAvantEtArriere(dechargeDetails.isParChocAvantEtArriere());
     * decharge.setSerruresEtVerrouillage(dechargeDetails.isSerruresEtVerrouillage()
     * );
     * decharge.setFonctionnementDesVitres(dechargeDetails.isFonctionnementDesVitres
     * ());
     * decharge.setEtatDhabillage(dechargeDetails.isEtatDhabillage());
     * 
     * return dechargeRepository.save(decharge);
     * }
     */
    public Decharge getDechargeById(Long id) {
        return dechargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Decharge not found with id: " + id));
    }

    public List<Decharge> getAllDecharges() {
        return dechargeRepository.findAll();
    }

    public void deleteDecharge(Long id) {
        Decharge decharge = dechargeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Decharge not found with id: " + id));
        dechargeRepository.delete(decharge);
    }
}