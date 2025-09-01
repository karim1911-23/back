package dislog.cs.cs.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dislog.cs.cs.service.UploadDataExcelService;

@RestController
@RequestMapping("/api/admin")
public class UploadDataExcelController {
    @Autowired
    private UploadDataExcelService uploadDataExcelService;

    @GetMapping("/generateRemorque")
    public ResponseEntity<byte[]> downloadExcelR() {
        try {
            byte[] excelData = uploadDataExcelService.generateExcelFileR();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("Remorque.xlsx", java.nio.charset.StandardCharsets.UTF_8) // 💡 Ajout important
                    .build());

            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/generateVehicule")
    public ResponseEntity<byte[]> downloadExcelV() {
        try {
            byte[] excelData = uploadDataExcelService.generateExcelFileV();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("Vehicule.xlsx", java.nio.charset.StandardCharsets.UTF_8) // 💡 Ajout important
                    .build());

            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/generateCollabolateur")
    public ResponseEntity<byte[]> downloadExcelC() {
        try {
            byte[] excelData = uploadDataExcelService.generateExcelFileC();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("Collabolateur.xlsx", java.nio.charset.StandardCharsets.UTF_8) // 💡 Ajout important
                    .build());

            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/uploadCollab")
    public ResponseEntity<String> uploadTrancheGhExcel(@RequestParam("file") MultipartFile file) throws IOException {
        uploadDataExcelService.uploadDataCollabolateur(file.getInputStream());
        return ResponseEntity.ok("uploaded");
    }

    @PostMapping("/uploadVehicule")
    public ResponseEntity<String> uploadVExcel(@RequestParam("file") MultipartFile file) throws IOException {
        uploadDataExcelService.uploadDataVehicule(file.getInputStream());
        return ResponseEntity.ok("uploaded");
    }

    @PostMapping("/uploadRemorque")
    public ResponseEntity<String> uploadRExcel(@RequestParam("file") MultipartFile file) throws IOException {
        uploadDataExcelService.uploadDataRemorque(file.getInputStream());
        return ResponseEntity.ok("uploaded");
    }

}
