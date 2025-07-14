package dislog.cs.cs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
@RequestMapping("/admin/api/files")
public class UploadImageController {

    private final String uploadDir = "cs/src/main/java/dislog/cs/cs/files";

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

    @GetMapping("/image/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get("src/main/java/com/dislog/dislog/files").resolve(filename);
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(imagePath);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE,
                                contentType != null ? contentType : "application/octet-stream")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/image/{filename}")
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

}
