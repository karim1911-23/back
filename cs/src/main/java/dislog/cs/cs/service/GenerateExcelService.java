package dislog.cs.cs.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Activite;
import dislog.cs.cs.model.Categorie;
import dislog.cs.cs.model.Departement;
import dislog.cs.cs.model.Poste;
import dislog.cs.cs.model.Site;

@Service
public class GenerateExcelService {

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private DepartementService departementService;

    @Autowired
    private PosteService posteService;

    @Autowired
    private ServiceCollaborateurService scs;

    @Autowired
    private SiteService siteService;

    public byte[] generateExcelFile() throws Exception {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // 1. Feuille Postes
            Sheet postesSheet = workbook.createSheet("Postes");
            Row rowPoste = postesSheet.createRow(0);
            rowPoste.createCell(0).setCellValue("Poste");

            // 2. Feuille Sites
            Sheet sitesSheet = workbook.createSheet("Sites");
            Row rowSite = sitesSheet.createRow(0);
            rowSite.createCell(0).setCellValue("Site");

            // 3. Feuille Départements
            Sheet depSheet = workbook.createSheet("Départements");
            Row rowDep = depSheet.createRow(0);
            rowDep.createCell(0).setCellValue("Département");

            // 4. Feuille Catégories
            Sheet catSheet = workbook.createSheet("Catégories");
            Row rowCat = catSheet.createRow(0);
            rowCat.createCell(0).setCellValue("Catégorie");

            // 5. Feuille Activités
            Sheet actSheet = workbook.createSheet("Activités");
            Row rowAct = actSheet.createRow(0);
            rowAct.createCell(0).setCellValue("Activité");

            // 6. Feuille Services
            Sheet servSheet = workbook.createSheet("Services");
            Row rowServ = servSheet.createRow(0);
            rowServ.createCell(0).setCellValue("Service");

            // Écrire le fichier
            workbook.write(out);
            return out.toByteArray();
        }
    }

    public void uploadData(InputStream file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);

        // 1. Feuille Postes
        Sheet postesSheet = workbook.getSheet("Postes");
        if (postesSheet != null) {
            for (Row row : postesSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && !cell.getStringCellValue().trim().isEmpty()) {
                    Poste poste = new Poste();
                    poste.setPoste(cell.getStringCellValue().trim());
                    posteService.create(poste);
                }
            }
        }

        // 2. Feuille Sites
        Sheet sitesSheet = workbook.getSheet("Sites");
        if (sitesSheet != null) {
            for (Row row : sitesSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && !cell.getStringCellValue().trim().isEmpty()) {
                    Site site = new Site();
                    site.setSite(cell.getStringCellValue().trim());
                    siteService.create(site);
                }
            }
        }

        // 3. Feuille Départements
        Sheet depSheet = workbook.getSheet("Départements");
        if (depSheet != null) {
            for (Row row : depSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && !cell.getStringCellValue().trim().isEmpty()) {
                    Departement dep = new Departement();
                    dep.setDepartement(cell.getStringCellValue().trim());
                    departementService.create(dep);
                }
            }
        }

        // 4. Feuille Catégories
        Sheet catSheet = workbook.getSheet("Catégories");
        if (catSheet != null) {
            for (Row row : catSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && !cell.getStringCellValue().trim().isEmpty()) {
                    Categorie cat = new Categorie();
                    cat.setCategorie(cell.getStringCellValue().trim());
                    categorieService.create(cat);
                }
            }
        }

        // 5. Feuille Activités
        Sheet actSheet = workbook.getSheet("Activités");
        if (actSheet != null) {
            for (Row row : actSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && !cell.getStringCellValue().trim().isEmpty()) {
                    Activite act = new Activite();
                    act.setActivite(cell.getStringCellValue().trim());
                    activiteService.create(act);
                }
            }
        }

        // 6. Feuille Services
        Sheet servSheet = workbook.getSheet("Services");
        if (servSheet != null) {
            for (Row row : servSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && !cell.getStringCellValue().trim().isEmpty()) {
                    dislog.cs.cs.model.Service s = new dislog.cs.cs.model.Service();
                    s.setService(cell.getStringCellValue().trim());
                    scs.create(s);
                }
            }
        }

        workbook.close();
    }

}
