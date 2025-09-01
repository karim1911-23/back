package dislog.cs.cs.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dislog.cs.cs.model.Activite;
import dislog.cs.cs.model.Categorie;
import dislog.cs.cs.model.Collaborateur;
import dislog.cs.cs.model.Departement;
import dislog.cs.cs.model.Energie;
import dislog.cs.cs.model.Marque;
import dislog.cs.cs.model.Modele;
import dislog.cs.cs.model.Poste;
import dislog.cs.cs.model.ProprieteCarteGrise;
import dislog.cs.cs.model.Remorque;
import dislog.cs.cs.model.Site;
import dislog.cs.cs.model.Type;
import dislog.cs.cs.model.Tonnage;
import dislog.cs.cs.model.Vehicule;
import dislog.cs.cs.model.utils.GenreRemorque;
import dislog.cs.cs.model.utils.MarqueRemorque;
import dislog.cs.cs.model.utils.ProprieteRemorque;
import dislog.cs.cs.model.utils.TonnageRemorque;

@Service
public class UploadDataExcelService {
    @Autowired
    private PosteService posteService;
    @Autowired
    private ActiviteService activiteService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private ServiceCollaborateurService scs;
    @Autowired
    private DepartementService departementService;
    @Autowired
    private CategorieService categorieService;
    @Autowired
    private EnergieService energieService;
    @Autowired
    private MarqueService marqueService;
    @Autowired
    private ModeleService modeleService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TonnageService tonnageService;
    @Autowired
    private ProprieteCarteGriseService proprieteCarteGriseService;
    @Autowired
    private RemorqueService remorqueService;
    @Autowired
    private CollaborateurService collaborateurService;

    @Autowired
    private VehiculeService vehiculeService;

    public void uploadDataCollabolateur(InputStream file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);

        Sheet sheet = workbook.getSheetAt(0);
        sheet.forEach(r -> {
            if (r.getRowNum() != 0) {
                Collaborateur c = new Collaborateur();

                c.setMatricule(getStringCellValue(r.getCell(0)));
                c.setNom(getStringCellValue(r.getCell(1)));
                c.setPrenom(getStringCellValue(r.getCell(2)));

                Poste p = posteService.getByPoste(getStringCellValue(r.getCell(3)));
                c.setPoste(p);

                Site s = siteService.getBySite(getStringCellValue(r.getCell(4)));
                c.setSite(s);

                Departement d = departementService.getByDepartement(getStringCellValue(r.getCell(5)));
                c.setDepartement(d);

                Categorie catego = categorieService.getByCategorie(getStringCellValue(r.getCell(6)));
                c.setCategorie(catego);

                Activite a = activiteService.getByActivite(getStringCellValue(r.getCell(7)));
                c.setActivite(a);

                dislog.cs.cs.model.Service ser = scs.getByService(getStringCellValue(r.getCell(8)));
                c.setService(ser);

                c.setCtr(getStringCellValue(r.getCell(9)));
                c.setDateAnc(getDateAsString(r.getCell(10)));
                c.setDateEmb(getDateAsString(r.getCell(11)));
                c.setDateNaissance(getDateAsString(r.getCell(12)));
                c.setCin(getStringCellValue(r.getCell(13)));
                c.setCnss(getStringCellValue(r.getCell(14)));
                c.setDateDepart(getDateAsString(r.getCell(15)));
                c.setMotifDepart(getStringCellValue(r.getCell(16)));
                c.setStc(getStringCellValue(r.getCell(17)));

                collaborateurService.save(c);
            }
        });

        workbook.close();
    }

    public void uploadDataVehicule(InputStream file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);

        Sheet sheet = workbook.getSheetAt(0);
        sheet.forEach(r -> {
            if (r.getRowNum() != 0) {
                Vehicule v = new Vehicule();
                v.setMatAX(getStringCellValue(r.getCell(0)));
                v.setMatricule(getStringCellValue(r.getCell(1)));
                v.setWw(getStringCellValue(r.getCell(2)));
                v.setNumeroChasse(getStringCellValue(r.getCell(3)));
                ProprieteCarteGrise pcg = new ProprieteCarteGrise();
                pcg = proprieteCarteGriseService.getByProprieteCarteGrise(getStringCellValue(r.getCell(4)));
                v.setProprieteCarteGrise(pcg);
                Marque m = new Marque();
                m = marqueService.getByMarque(getStringCellValue(r.getCell(5)));
                v.setMarque(m);
                Modele mpd = new Modele();
                mpd = modeleService.getByModele(getStringCellValue(r.getCell(6)));
                v.setModele(mpd);
                v.setDmc(getDateAsString(r.getCell(7)));
                Tonnage t = new Tonnage();
                t = tonnageService.getByTonnage(getStringCellValue(r.getCell(8)));
                v.setTonnage(t);
                Type ty = new Type();
                ty = typeService.getByType(getStringCellValue(r.getCell(9)));
                v.setType(ty);
                v.setTypeVehicule(getStringCellValue(r.getCell(10)));
                Remorque re = new Remorque();
                re = remorqueService.getByMatrcule(getStringCellValue(r.getCell(11)));
                v.setRemorque(re);
                Energie e = new Energie();
                e = energieService.getByEnergie(getStringCellValue(r.getCell(12)));
                v.setEnergie(e);
                vehiculeService.create(v);
            }
        });

        workbook.close();
    }

    public void uploadDataRemorque(InputStream file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        sheet.forEach(r -> {
            if (r.getRowNum() != 0) {
                Remorque remorque = new Remorque();

                // Matricule
                remorque.setMatricule(getStringCellValue(r.getCell(0)));

                // ENUMS mappés par "value" avec gestion accents
                remorque.setMarque(getStringCellValue(r.getCell(1)));
                remorque.setGenre(getStringCellValue(r.getCell(2)));
                remorque.setTonnage(getStringCellValue(r.getCell(3)));
                remorque.setPropriete(getStringCellValue(r.getCell(4)));

                // DMC : date ou texte au format "dd.MM.yyyy"
                remorque.setDmc(getDateAsString(r.getCell(4)));

                // Sauvegarde en DB
                remorqueService.create(remorque);
            }
        });

        workbook.close();
    }

    private <E extends Enum<E>> E getEnumByValue(Class<E> enumType, String excelValue) {
        if (excelValue == null || excelValue.isEmpty())
            return null;

        String normalizedExcelValue = normalize(excelValue);

        for (E e : enumType.getEnumConstants()) {
            try {
                String val = (String) enumType.getMethod("getValue").invoke(e);
                if (normalize(val).equals(normalizedExcelValue)) {
                    return e;
                }
            } catch (Exception ignored) {
            }
        }
        throw new IllegalArgumentException("Valeur inconnue pour " + enumType.getSimpleName() + " : " + excelValue);
    }

    /**
     * Normalise une chaîne : supprime accents + minuscule
     */
    private String normalize(String input) {
        if (input == null)
            return "";
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase().trim();
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null)
            return "";
        cell.setCellType(CellType.STRING); // force la lecture en String
        return cell.getStringCellValue().trim();
    }

    private String getDateAsString(Cell cell) {
        if (cell == null)
            return "";
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            // formater au format dd.MM.yyyy
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            return sdf.format(cell.getDateCellValue());
        }
        return ""; // si vide ou pas une date
    }

    public byte[] generateExcelFileR() throws Exception {
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // 1. Feuille Remorque
            Sheet remorqueSheet = workbook.createSheet("Remorque");

            // Style pour les en-têtes
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Police en gras pour les en-têtes
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // Bordure des cellules
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            // Création de la ligne d'en-tête
            Row headerRow = remorqueSheet.createRow(0);

            // Tableau des en-têtes
            String[] headers = { "Matricule", "MARQUE", "Genre", "TONNAGE", "DMC", "PROPRIETE" };

            // Ajout des en-têtes avec style
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);

                // Ajustement automatique de la largeur des colonnes
                remorqueSheet.autoSizeColumn(i);
            }

            // Écriture dans le flux de sortie
            workbook.write(out);
            return out.toByteArray();
        }
    }

    public byte[] generateExcelFileV() throws Exception {
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Création de la feuille Véhicules
            Sheet vehiculeSheet = workbook.createSheet("Véhicules");

            // Style pour les en-têtes
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Police en gras pour les en-têtes
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex()); // Texte blanc
            headerStyle.setFont(headerFont);

            // Bordures des cellules
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            // Création de la ligne d'en-tête
            Row headerRow = vehiculeSheet.createRow(0);

            // Colonnes selon l'image
            String[] headers = {
                    "MAT AX",
                    "MAT",
                    "WW",
                    "Numero Chassis",
                    "PROPRIETE CARTE GRISE",
                    "MARQUE",
                    "MODEL",
                    "DMC",
                    "TONNAGE",
                    "Type",
                    "TYPE TRUCK/FOURGON",
                    "MAT SEMI-REMORQUE",
                    "ENERGIE"
            };

            // Ajout des en-têtes avec style
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
                // Ajustement automatique de la largeur des colonnes
                vehiculeSheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }

    public byte[] generateExcelFileC() throws Exception {
        try (Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Création de la feuille Collaborateurs
            Sheet collabSheet = workbook.createSheet("Collaborateurs");

            // Style pour les en-têtes
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex()); // Couleur bleu-vert
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Police pour les en-têtes
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerFont.setFontHeightInPoints((short) 11);
            headerStyle.setFont(headerFont);

            // Bordures et alignement
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Style pour les dates
            CellStyle dateStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));

            // Création de la ligne d'en-tête
            Row headerRow = collabSheet.createRow(0);

            // Colonnes selon les images
            String[] headers = {
                    "MAT",
                    "NOM",
                    "PRENOM",
                    "POSTE",
                    "SITE",
                    "DEPART",
                    "CATEGORIE",
                    "ACTIVITE",
                    "SERVICE",
                    "CTR",
                    "DATE ANC",
                    "DATE EMB",
                    "DATE NAISSANCE",
                    "CIN",
                    "CNSS",
                    "DATE DEPART",
                    "MOTIF DEPART",
                    "STC"
            };

            // Ajout des en-têtes avec style
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
                collabSheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}
