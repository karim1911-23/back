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
import dislog.cs.cs.model.Energie;
import dislog.cs.cs.model.LieuArret;
import dislog.cs.cs.model.Marque;
import dislog.cs.cs.model.Modele;
import dislog.cs.cs.model.Poste;
import dislog.cs.cs.model.ProprieteCarteGrise;
import dislog.cs.cs.model.Region;
import dislog.cs.cs.model.Site;
import dislog.cs.cs.model.Tonnage;
import dislog.cs.cs.model.Type;

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

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProprieteCarteGriseService pcgService;
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModeleService modeleService;
    @Autowired
    private TonnageService tonnageService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private LieuArretService lieuArretService;
    @Autowired
    private EnergieService energieService;

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

        // Helper method to normalize sheet names
        processSheet(workbook, "Postes", "Poste", this::createPoste);
        processSheet(workbook, "Sites", "Site", this::createSite);
        processSheet(workbook, "Départements", "Départements", this::createDepartement);
        processSheet(workbook, "Catégories", "Catégorie", this::createCategorie);
        processSheet(workbook, "Activités", "Activité", this::createActivite);
        processSheet(workbook, "Services", "Service", this::createService);

        workbook.close();
    }

    private void processSheet(Workbook workbook, String sheetName, String fieldName, Creator creator) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            return;
        }

        int rowCount = 0;
        int insertedCount = 0;
        for (Row row : sheet) {
            if (row.getRowNum() == 0) { // Skip header row
                continue;
            }
            rowCount++;
            Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            String value = getCellValueAsString(cell);
            if (value != null && !value.trim().isEmpty()) {
                try {
                    creator.create(value.trim());
                    insertedCount++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("log");
            }
        }
        System.out.println("log");
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BLANK:
            case ERROR:
            default:
                return null;
        }
    }

    public void uploadDataV(InputStream file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);

        // 1. Propriété Carte Grise
        Sheet pcgSheet = workbook.getSheet("propriété carte grise");
        if (pcgSheet != null) {
            for (Row row : pcgSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    ProprieteCarteGrise pcg = new ProprieteCarteGrise();
                    pcg.setProprieteCarteGrise(cell.getStringCellValue().trim());
                    pcgService.add(pcg);
                }
            }
        }

        // 2. Marque
        Sheet marqueSheet = workbook.getSheet("marque");
        if (marqueSheet != null) {
            for (Row row : marqueSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    Marque marque = new Marque();
                    marque.setMarque(cell.getStringCellValue().trim());
                    marqueService.add(marque);
                }
            }
        }

        Sheet modelSheet = workbook.getSheet("model");
        if (modelSheet != null) {
            for (Row row : modelSheet) {
                if (row.getRowNum() == 0)
                    continue;

                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    Modele modele = new Modele(); // ✔️ variable déclarée
                    modele.setModele(cell.getStringCellValue().trim()); // ✔️ méthode utilisée correctement
                    modeleService.add(modele); // ✔️ envoie au service
                }
            }
        }

        // 4. Tonnage
        Sheet tonnageSheet = workbook.getSheet("tonnage");
        if (tonnageSheet != null) {
            for (Row row : tonnageSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    Tonnage tonnage = new Tonnage();
                    tonnage.setTonnage(cell.getStringCellValue().trim());
                    tonnageService.add(tonnage);
                }
            }
        }

        // 5. Type
        Sheet typeSheet = workbook.getSheet("type");
        if (typeSheet != null) {
            for (Row row : typeSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    Type type = new Type();
                    type.setType(cell.getStringCellValue().trim());
                    typeService.add(type);
                }
            }
        }

        // 6. MAT SEMI-REMORQUE

        // 7. LIEU DARRET
        Sheet lieuSheet = workbook.getSheet("LIEU DARRET");
        if (lieuSheet != null) {
            for (Row row : lieuSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    LieuArret lieu = new LieuArret();
                    lieu.setLieu(cell.getStringCellValue().trim());
                    lieuArretService.add(lieu);
                }
            }
        }

        // 8. ENERGIE
        Sheet energieSheet = workbook.getSheet("ENERGIE");
        if (energieSheet != null) {
            for (Row row : energieSheet) {
                if (row.getRowNum() == 0)
                    continue;
                Cell cell = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
                    Energie energie = new Energie();
                    energie.setEnergie(cell.getStringCellValue().trim());
                    energieService.create(energie);
                }
            }
        }

        workbook.close();
    }

    private interface Creator {
        void create(String value);
    }

    private void createPoste(String value) {
        Poste poste = new Poste();
        poste.setPoste(value);
        posteService.create(poste);
    }

    private void createSite(String value) {
        Site site = new Site();
        site.setSite(value);
        siteService.create(site);
    }

    private void createDepartement(String value) {
        Departement dep = new Departement();
        dep.setDepartement(value);
        departementService.create(dep);
    }

    private void createCategorie(String value) {
        Categorie cat = new Categorie();
        cat.setCategorie(value);
        categorieService.create(cat);
    }

    private void createActivite(String value) {
        Activite act = new Activite();
        act.setActivite(value);
        activiteService.create(act);
    }

    private void createService(String value) {
        // Assuming dislog.cs.cs.model.Service was a typo; replace with correct Service
        // class
        dislog.cs.cs.model.Service service = new dislog.cs.cs.model.Service();
        service.setService(value);
        scs.create(service); // Replace scs with serviceService
    }

    public void uploadDataR(InputStream file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);

        // 1. Propriété Carte Grise
        Sheet sheet = workbook.getSheetAt(0);
        sheet.forEach(r -> {
            Region region = new Region();
            if (r.getRowNum() != 0) {
                region.setNom(r.getCell(0).getStringCellValue());
                regionService.createRegion(region);
            }
        });
        workbook.close();
    }

    
}
