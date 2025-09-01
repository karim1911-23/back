package dislog.cs.cs.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import dislog.cs.cs.model.Affectation;

@Service
public class PdfPassationService {

    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD,
            new java.awt.Color(15, 46, 84));
    private static final Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD,
            new java.awt.Color(15, 46, 84));
    private static final Font SUBHEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD,
            new java.awt.Color(51, 51, 51));
    private static final Font NORMAL_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL,
            java.awt.Color.BLACK);
    private static final Font LABEL_FONT = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD,
            new java.awt.Color(51, 51, 51));
    private static final java.awt.Color HEADER_BG_COLOR = new java.awt.Color(230, 240, 250);
    private static final java.awt.Color ROW_BG_COLOR = new java.awt.Color(245, 249, 255);

    @Autowired
    private AffectationService affectationService;

    public ByteArrayInputStream generatePdf(Long id) throws Exception {
        Affectation a = affectationService.getAffectationById(id);
        if (a == null) {
            throw new RuntimeException("Affectation introuvable avec id: " + id);
        }

        Document document = new Document(PageSize.A4, 40, 40, 60, 60);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // En-tête avec logo et titre
            addHeader(document, a);

            // Informations principales
            addMainInfoSection(document, a);

            // Section véhicule
            addVehicleSection(document, a);

            // Section collaborateur
            addCollaboratorSection(document, a);

            // Détails de passation
            addPassationDetails(document, a);

            // État du véhicule
            addVehicleConditionSection(document, a);

            // Images
            addImagesSection(document, a);

            // Section signature
            addSignatureSection(document);

            document.close();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF: " + e.getMessage(), e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addHeader(Document document, Affectation a) throws Exception {
        // Logo (à remplacer par votre propre logo)
        /*
         * Image logo = Image.getInstance("path/to/your/logo.png");
         * logo.scaleToFit(100, 60);
         * logo.setAlignment(Element.ALIGN_LEFT);
         * document.add(logo);
         */

        // Titre principal
        Paragraph title = new Paragraph("DISLOG COMPTOIR SERVICE", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(10);
        document.add(title);

        // Sous-titre avec type de véhicule
        String vehicleType = a.getVehicule() != null ? a.getVehicule().getType().getType() : "TRACTEUR";
        Paragraph subTitle = new Paragraph("FICHE DE PASSATION - " + vehicleType.toUpperCase(), HEADER_FONT);
        subTitle.setAlignment(Element.ALIGN_CENTER);
        subTitle.setSpacingAfter(20);
        document.add(subTitle);

        // Ligne de séparation
        addSeparator(document);
    }

    private void addMainInfoSection(Document document, Affectation a) throws Exception {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 1, 1 });
        table.setSpacingBefore(10);
        table.setSpacingAfter(15);

        // Cellule pour la date et heure
        PdfPCell dateCell = new PdfPCell(
                new Phrase("Date: " + (a.getDateAffectation() != null ? a.getDateAffectation() : ""), NORMAL_FONT));
        dateCell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(dateCell);

        // Cellule pour le matricule
        String matricule = a.getVehicule() != null ? a.getVehicule().getMatricule() : "";
        PdfPCell matriculeCell = new PdfPCell(new Phrase("Matricule: " + matricule, NORMAL_FONT));
        matriculeCell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(matriculeCell);

        // Cellule pour prise en charge
        PdfPCell priseEnChargeCell = new PdfPCell(
                new Phrase("Prise en charge: " + (a.getHeureDebut() != null ? a.getHeureDebut() : ""), NORMAL_FONT));
        priseEnChargeCell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(priseEnChargeCell);

        // Cellule pour fin décharge
        PdfPCell finDechargeCell = new PdfPCell(new Phrase("Fin décharge: ", NORMAL_FONT));
        finDechargeCell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(finDechargeCell);

        document.add(table);
        addSeparator(document);
    }

    private void addVehicleSection(Document document, Affectation a) throws Exception {
        if (a.getVehicule() == null)
            return;

        Paragraph sectionTitle = new Paragraph("INFORMATIONS VÉHICULE", SUBHEADER_FONT);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 1, 2 });
        table.setSpacingAfter(15);

        addTableHeaderRow(table, "Marque:", a.getVehicule().getMarque().getMarque());
        addTableHeaderRow(table, "Modèle:", a.getVehicule().getModele().getModele());
        addTableHeaderRow(table, "Type:", a.getVehicule().getType().getType());
        addTableHeaderRow(table, "Kilométrage:", a.getKilometres());
        addTableHeaderRow(table, "Habillage:", a.getVehicule().isHabillage() ? "Oui" : "Non");

        document.add(table);
        addSeparator(document);
    }

    private void addCollaboratorSection(Document document, Affectation a) throws Exception {
        if (a.getCollaborateur() == null)
            return;

        Paragraph sectionTitle = new Paragraph("INFORMATIONS COLLABORATEUR", SUBHEADER_FONT);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 1, 2 });
        table.setSpacingAfter(15);

        addTableHeaderRow(table, "Nom:", a.getCollaborateur().getPrenom() + " " + a.getCollaborateur().getNom());
        addTableHeaderRow(table, "Poste:", a.getCollaborateur().getPoste().getPoste());
        addTableHeaderRow(table, "Service:", a.getCollaborateur().getService().getService());
        addTableHeaderRow(table, "Département:", a.getCollaborateur().getDepartement().getDepartement());

        document.add(table);
        addSeparator(document);
    }

    private void addPassationDetails(Document document, Affectation a) throws Exception {
        if (a.getPassation() == null)
            return;

        Paragraph sectionTitle = new Paragraph("DOCUMENTS ET ACCESSOIRES", SUBHEADER_FONT);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 2, 1, 2, 1 });
        table.setSpacingAfter(15);

        // En-tête du tableau
        PdfPCell header1 = new PdfPCell(new Phrase("Document", LABEL_FONT));
        PdfPCell header2 = new PdfPCell(new Phrase("Présent", LABEL_FONT));
        PdfPCell header3 = new PdfPCell(new Phrase("Accessoire", LABEL_FONT));
        PdfPCell header4 = new PdfPCell(new Phrase("Présent", LABEL_FONT));

        header1.setBackgroundColor(HEADER_BG_COLOR);
        header2.setBackgroundColor(HEADER_BG_COLOR);
        header3.setBackgroundColor(HEADER_BG_COLOR);
        header4.setBackgroundColor(HEADER_BG_COLOR);

        table.addCell(header1);
        table.addCell(header2);
        table.addCell(header3);
        table.addCell(header4);

        // Lignes du tableau - Documents
        addPassationRow(table, "Carte grise", a.getPassation().isCarteGrise());
        addPassationRow(table, "Assurance", a.getPassation().isAssurance());
        addPassationRow(table, "Visite technique", a.getPassation().isVisiteTechnique());
        addPassationRow(table, "Vignette", a.getPassation().isVignette());
        addPassationRow(table, "Carnet circulation/Taxe tonnage", a.getPassation().isCarnetCirculationTaxeTonnage());
        addPassationRow(table, "Carnet tachygraphe", a.getPassation().isCarnetTachygraphe());

        // Lignes du tableau - Accessoires
        addPassationRow(table, "Extincteurs", a.getPassation().isExtincteurs());
        addPassationRow(table, "Triangle de panne", a.getPassation().isTriangleDePanne());
        addPassationRow(table, "Cric/Clé roue", a.getPassation().isCricCleRoue());
        addPassationRow(table, "Manivelle", a.getPassation().isManivelle());
        addPassationRow(table, "Pneu de secours", a.getPassation().isPneuDeSecours());
        addPassationRow(table, "Poste radio", a.getPassation().isPosteRadio());

        document.add(table);
        addSeparator(document);
    }

    private void addVehicleConditionSection(Document document, Affectation a) throws Exception {
        if (a.getPassation() == null)
            return;

        Paragraph sectionTitle = new Paragraph("ÉTAT DU VÉHICULE", SUBHEADER_FONT);
        sectionTitle.setSpacingBefore(15);
        sectionTitle.setSpacingAfter(10);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(new float[] { 2, 1 });
        table.setSpacingAfter(15);

        // En-tête du tableau
        PdfPCell header1 = new PdfPCell(new Phrase("Élément", LABEL_FONT));
        PdfPCell header2 = new PdfPCell(new Phrase("État", LABEL_FONT));

        header1.setBackgroundColor(HEADER_BG_COLOR);
        header2.setBackgroundColor(HEADER_BG_COLOR);

        table.addCell(header1);
        table.addCell(header2);

        // Lignes du tableau
        addConditionRow(table, "Aucune fissure des vitres", a.getPassation().isAucuneFissureDesVitres());
        addConditionRow(table, "État de la cabine", a.getPassation().isEtatDeLaCabine());
        addConditionRow(table, "État de la caisse", a.getPassation().isEtatDeLaCaisse());
        addConditionRow(table, "État des pneus", a.getPassation().isEtatDesPneus());
        addConditionRow(table, "État des batteries", a.getPassation().isEtatDesBatteries());
        addConditionRow(table, "État des rétroviseurs", a.getPassation().isEtatDesRetroviseurs());
        addConditionRow(table, "Signaux/Clignotants", a.getPassation().isSignalesClignotants());
        addConditionRow(table, "Feux arrière/Feux de stop", a.getPassation().isFeuxArriereFeuxDeStop());
        addConditionRow(table, "État du moteur", a.getPassation().isEtatMoteur());
        addConditionRow(table, "Par-chocs avant/arrière", a.getPassation().isParChocAvantEtArriere());
        addConditionRow(table, "Serrures et verrouillage", a.getPassation().isSerruresEtVerrouillage());
        addConditionRow(table, "Fonctionnement des vitres", a.getPassation().isFonctionnementDesVitres());
        addConditionRow(table, "Câble de sellage", a.getPassation().isCableDeSellage());
        addConditionRow(table, "État d'habillage", a.getPassation().isEtatDhabillage());
        addConditionRow(table, "Flexible de gonflage", a.getPassation().isFlexibleDeGonflage());
        addConditionRow(table, "État des freins", a.getPassation().isFonctionnementDesFreins());
        addConditionRow(table, "État du siège", a.getPassation().isEtatDeSiege());
        addConditionRow(table, "État de la calandre", a.getPassation().isEtatDeCalandre());
        addConditionRow(table, "État du caisson", a.getPassation().isEtatDeCaisson());

        document.add(table);
        addSeparator(document);
    }
    private void addImagesSection(Document document, Affectation a) throws Exception {
        if (a.getPassation() == null)
            return;

        List<String> imagesPaths = new ArrayList<>();
        String basePath = System.getProperty("user.dir") + "/cs/uploads/";

        if (a.getPassation().getImg1() != null)
            imagesPaths.add(basePath + a.getPassation().getImg1());
        if (a.getPassation().getImg2() != null)
            imagesPaths.add(basePath + a.getPassation().getImg2());
        if (a.getPassation().getImg3() != null)
            imagesPaths.add(basePath + a.getPassation().getImg3());
        if (a.getPassation().getImg4() != null)
            imagesPaths.add(basePath + a.getPassation().getImg4());
        if (a.getPassation().getImg5() != null)
            imagesPaths.add(basePath + a.getPassation().getImg5());
        if (a.getPassation().getImg6() != null)
            imagesPaths.add(basePath + a.getPassation().getImg6());
        if (a.getPassation().getImg7() != null)
            imagesPaths.add(basePath + a.getPassation().getImg7());
        if (a.getPassation().getImg8() != null)
            imagesPaths.add(basePath + a.getPassation().getImg8());
        if (a.getPassation().getImg9() != null)
            imagesPaths.add(basePath + a.getPassation().getImg9());
        if (a.getPassation().getImg10() != null)
            imagesPaths.add(basePath + a.getPassation().getImg10());

        if (!imagesPaths.isEmpty()) {
            Paragraph sectionTitle = new Paragraph("PHOTOS DU VÉHICULE", SUBHEADER_FONT);
            sectionTitle.setSpacingBefore(15);
            sectionTitle.setSpacingAfter(10);
            document.add(sectionTitle);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingAfter(15);

            for (String imgPath : imagesPaths) {
                File imgFile = new File(imgPath);
                if (imgFile.exists()) {
                    PdfPCell cell = new PdfPCell();
                    cell.setBorder(PdfPCell.NO_BORDER);
                    cell.setPadding(5);

                    Image img = Image.getInstance(imgPath);
                    img.scaleToFit(250, 180);
                    cell.addElement(img);

                    table.addCell(cell);
                }
            }

            // Compléter le tableau si nombre impair d'images
            if (imagesPaths.size() % 2 != 0) {
                table.addCell(new PdfPCell(new Phrase("")));
            }

            document.add(table);
            addSeparator(document);
        }
    }

    private void addSignatureSection(Document document) throws Exception {
        Paragraph sectionTitle = new Paragraph("SIGNATURES", SUBHEADER_FONT);
        sectionTitle.setSpacingBefore(20);
        sectionTitle.setSpacingAfter(15);
        document.add(sectionTitle);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);
        table.setSpacingAfter(20);

        // Signature conducteur actuel
        PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(PdfPCell.NO_BORDER);
        cell1.setPadding(10);

        Paragraph p1 = new Paragraph("Conducteur actuel:", LABEL_FONT);
        p1.setSpacingAfter(30);
        cell1.addElement(p1);
        cell1.addElement(new Paragraph("Signature:", LABEL_FONT));
        cell1.addElement(new Paragraph(" "));
        cell1.addElement(new Paragraph("Date: ____/____/____", NORMAL_FONT));
        cell1.addElement(new Paragraph("Heure: ______", NORMAL_FONT));

        // Signature ancien conducteur
        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(PdfPCell.NO_BORDER);
        cell2.setPadding(10);

        Paragraph p2 = new Paragraph("Ancien conducteur:", LABEL_FONT);
        p2.setSpacingAfter(30);
        cell2.addElement(p2);
        cell2.addElement(new Paragraph("Signature:", LABEL_FONT));
        cell2.addElement(new Paragraph(" "));
        cell2.addElement(new Paragraph("Date: ____/____/____", NORMAL_FONT));
        cell2.addElement(new Paragraph("Heure: ______", NORMAL_FONT));

        table.addCell(cell1);
        table.addCell(cell2);
        document.add(table);
    }

    private void addSeparator(Document document) throws Exception {
        Paragraph separator = new Paragraph(" ");
        separator.setSpacingBefore(5);
        separator.setSpacingAfter(5);
        document.add(separator);

        PdfPTable line = new PdfPTable(1);
        line.setWidthPercentage(100);
        line.setSpacingBefore(5);
        line.setSpacingAfter(5);

        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(1);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setBackgroundColor(new java.awt.Color(200, 200, 200));
        line.addCell(cell);

        document.add(line);
    }

    private void addTableHeaderRow(PdfPTable table, String label, String value) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, LABEL_FONT));
        labelCell.setBackgroundColor(ROW_BG_COLOR);
        labelCell.setBorder(PdfPCell.NO_BORDER);
        labelCell.setPadding(5);

        PdfPCell valueCell = new PdfPCell(new Phrase(value != null ? value : "", NORMAL_FONT));
        valueCell.setBorder(PdfPCell.NO_BORDER);
        valueCell.setPadding(5);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    private void addPassationRow(PdfPTable table, String label, boolean present) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, NORMAL_FONT));
        labelCell.setBorder(PdfPCell.NO_BORDER);
        labelCell.setPadding(5);

        PdfPCell presentCell = new PdfPCell(new Phrase(present ? "Oui" : "Non", NORMAL_FONT));
        presentCell.setBorder(PdfPCell.NO_BORDER);
        presentCell.setPadding(5);

        table.addCell(labelCell);
        table.addCell(presentCell);

        // Ajouter des cellules vides pour les colonnes 3 et 4
        if (table.getNumberOfColumns() > 2) {
            table.addCell(new PdfPCell(new Phrase("")));
            table.addCell(new PdfPCell(new Phrase("")));
        }
    }

    private void addConditionRow(PdfPTable table, String label, boolean goodCondition) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, NORMAL_FONT));
        labelCell.setBorder(PdfPCell.NO_BORDER);
        labelCell.setPadding(5);

        PdfPCell conditionCell = new PdfPCell(new Phrase(goodCondition ? "Bon" : "À vérifier", NORMAL_FONT));
        conditionCell.setBorder(PdfPCell.NO_BORDER);
        conditionCell.setPadding(5);
        conditionCell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(labelCell);
        table.addCell(conditionCell);
    }
}