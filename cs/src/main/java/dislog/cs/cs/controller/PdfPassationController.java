package dislog.cs.cs.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.service.PdfPassationService;

@RestController
@RequestMapping("api/admin")
public class PdfPassationController {

    @Autowired
    private PdfPassationService pdfPassationService;

    private final String uploadDir = System.getProperty("user.dir") + "/cs/uploads";

    // --- 1) POST : Générer et sauvegarder un PDF ---
    @PostMapping("/pdf/{id}")
    public ResponseEntity<String> uploadGeneratedPdf(@PathVariable Long id) {
        try {
            ByteArrayInputStream bis = pdfPassationService.generatePdf(id);

            // Nom du fichier
            String filename = "passation_" + id + ".pdf";
            Path path = Paths.get(uploadDir, filename);

            // Créer dossier s'il n'existe pas
            Files.createDirectories(path.getParent());

            // Sauvegarder le PDF
            Files.write(path, bis.readAllBytes());

            return ResponseEntity.ok("PDF généré et sauvegardé : " + filename);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur génération/sauvegarde PDF : " + e.getMessage());
        }
    }

    // --- 2) GET : Charger PDF par filename ---
    @GetMapping("/pdf/load/{filename}")
    public ResponseEntity<byte[]> loadPdf(@PathVariable String filename) {
        try {
            Path path = Paths.get(uploadDir, filename);

            if (!Files.exists(path)) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = Files.readAllBytes(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=" + filename);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(fileContent);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    // --- (Optionnel) : ton existant pour afficher directement ---
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) throws Exception {
        ByteArrayInputStream bis = pdfPassationService.generatePdf(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=passation.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
