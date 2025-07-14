package dislog.cs.cs.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

@Service
public class UploadService {

    private final String uploadDir = "src/main/java/dislog/cs/cs/files";

    public String uploadSingleFile(MultipartFile file) {
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

    
}
