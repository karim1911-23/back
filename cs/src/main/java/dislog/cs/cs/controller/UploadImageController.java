package dislog.cs.cs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/adminuser/files")
public class UploadImageController {

    private final String uploadDir = System.getProperty("user.dir") + "/cs/uploads";

    @PostMapping("/uploadMultiple")
    public ResponseEntity<?> uploadMultipleFiles(
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("matricule") String matricule,
            @RequestParam("mois") String mois) {

        List<String> uploadedFiles = new ArrayList<>();
        int i = 1;

        for (MultipartFile file : files) {
            try {
                // Récupérer l'extension originale du fichier
                String originalName = file.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                // Construire le nom du fichier avec extension
                String fileName = StringUtils.cleanPath(matricule + "_" + mois + "_" + i + extension);
                // Créer le dossier si nécessaire
                Path uploadPath = Paths.get(uploadDir);
                Files.createDirectories(uploadPath);

                // Copier le fichier
                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    System.out.println("Uploading file: " + fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                uploadedFiles.add(fileName);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(uploadedFiles);
    }

    @PostMapping("/uploadMultipleP")
    public ResponseEntity<?> uploadMultipleFilesP(
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("matricule") String matricule,
            @RequestParam("date") String date,
            @RequestParam("type") String type,
            @RequestParam("heure") String heure) {

        List<String> uploadedFiles = new ArrayList<>();
        int i = 1;

        for (MultipartFile file : files) {
            try {
                // Récupérer l'extension originale du fichier
                String originalName = file.getOriginalFilename();
                String extension = "";

                if (originalName != null && originalName.contains(".")) {
                    extension = originalName.substring(originalName.lastIndexOf("."));
                }

                // Construire le nom du fichier avec extension
                // Nettoyer la date pour ne pas inclure de / ou \
                String safeDate = date.replaceAll("[/\\\\]", "-");

                String fileName = StringUtils
                        .cleanPath(type + "_" + matricule + "_" + safeDate + "_" + heure + "_" + i + extension);

                // Créer le dossier si nécessaire
                Path uploadPath = Paths.get(uploadDir);
                Files.createDirectories(uploadPath);

                // Copier le fichier
                try (InputStream inputStream = file.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    System.out.println("Uploading file: " + fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }

                uploadedFiles.add(fileName);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
            }
        }

        return ResponseEntity.ok(uploadedFiles);
    }

    @PostMapping("/upload")
    public String uploadSingleFile(@RequestParam("file") MultipartFile file) {
        try {
            // Nettoie le nom du fichier
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            // Crée le dossier si non existant
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);

            // Copie le fichier
            InputStream inputStream = file.getInputStream();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            return "Erreur d’upload ";
        }
    }

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            System.out.println("Recherche de l'image : " + filename);
            Path imagePath = Paths.get(uploadDir).resolve(filename);
            System.out.println("Recherche image dans : " + imagePath.toAbsolutePath());
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                System.out.println("Image trouvée et accessible : " + resource.getFilename());
                String contentType = Files.probeContentType(imagePath);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE,
                                contentType != null ? contentType : "application/octet-stream")
                        .body(resource);
            } else {
                System.out.println("Image inexistante ou non lisible");
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/delete/{filename:.+}")
    public ResponseEntity<String> deleteImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return ResponseEntity.ok("Image supprimée avec succès : " + filename);
            } else {
                return ResponseEntity.status(404).body("Image non trouvée : " + filename);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String filename) {
        try {
            // Récupération du chemin
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            // Vérification que le fichier existe et est lisible
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Déterminer le type MIME (ou forcer application/octet-stream)
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            // Renvoi du fichier avec l'entête pour forcer le téléchargement
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
