package dislog.cs.cs.controller;

import java.io.IOException;

import dislog.cs.cs.service.GenerateExcelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class GenerateExcelController {

    @Autowired
    private GenerateExcelService generateExcelService;

    @GetMapping("/generate")
    public ResponseEntity<byte[]> downloadExcel() {
        try {
            byte[] excelData = generateExcelService.generateExcelFile();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("model.xlsx", java.nio.charset.StandardCharsets.UTF_8) // 💡 Ajout important
                    .build());

            return new ResponseEntity<>(excelData, headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTrancheGhExcel(@RequestParam("file") MultipartFile file) throws IOException {
        generateExcelService.uploadData(file.getInputStream());
        return ResponseEntity.ok("uploaded");
    }

    @PostMapping("/uploadV")
    public ResponseEntity<String> uploadTrancheGhExcelV(@RequestParam("file") MultipartFile file) throws IOException {
        generateExcelService.uploadDataV(file.getInputStream());
        return ResponseEntity.ok("uploaded");
    }

    @PostMapping("/uploadR")
    public ResponseEntity<String> uploadTrancheGhExcelR(@RequestParam("file") MultipartFile file) throws IOException {
        generateExcelService.uploadDataR(file.getInputStream());
        return ResponseEntity.ok("uploaded");
    }

}
