package dislog.cs.cs.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class VerificationService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, VerificationData> verificationStorage = new HashMap<>();

    public String sendVerificationCode(String email) throws MessagingException {
        // Générer un code aléatoire de 6 chiffres
        String code = String.format("%06d", new Random().nextInt(999999));

        // Sauvegarder le code et son expiration (30 secondes)
        verificationStorage.put(email, new VerificationData(code, LocalDateTime.now().plusSeconds(30)));

        // Création du message MIME (HTML)
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject("🔑 Votre code de vérification");
        helper.setFrom("qasidkarim04@gmail.com");

        // ✅ Exemple HTML stylisé avec logo
        String htmlContent = """
                <div style="font-family: Arial, sans-serif; text-align: center; padding: 20px; background: #f4f6f9;">
                    <div style="max-width: 600px; margin: auto; background: white; border-radius: 8px; padding: 30px; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">
                        <img src="https://stagiairesdocs.s3.eu-west-3.amazonaws.com/wp-content/uploads/2024/10/02171859/Logo-STG-Entreprise-et-ecole-2024-10-02T171855.279.png" alt="Logo" style="width:120px; margin-bottom: 20px;" />
                        <h2 style="color:#333;">Vérification de votre compte</h2>
                        <p style="color:#555; font-size: 16px;">
                            Bonjour,<br><br>
                            Voici votre code de vérification pour confirmer votre action :
                        </p>
                        <div style="font-size: 28px; font-weight: bold; color: #51abac; letter-spacing: 5px; margin: 20px 0;">
                            """
                + code + """
                                    </div>
                                    <p style="color:#777; font-size: 14px;">
                                        Ce code expirera dans <b>30 secondes</b>. Merci de ne pas le partager.
                                    </p>
                                    <hr style="margin: 20px 0; border: none; border-top: 1px solid #eee;" />
                                    <p style="color:#999; font-size: 12px;">
                                        © 2025 Mon Entreprise. Tous droits réservés.
                                    </p>
                                </div>
                            </div>
                        """;

        helper.setText(htmlContent, true); // true => active HTML

        // Envoi du mail
        mailSender.send(mimeMessage);

        return "Code envoyé à l'adresse : " + email;
    }

    public String verifyCode(String email, String code) {
        VerificationData data = verificationStorage.get(email);

        if (data == null) {
            return "Aucun code trouvé pour cet e-mail.";
        }

        // Vérifier si le code est expiré
        if (LocalDateTime.now().isAfter(data.getExpirationTime())) {
            verificationStorage.remove(email);
            return "Le code a expiré.";
        }

        // Vérifier si le code correspond
        if (data.getCode().equals(code)) {
            verificationStorage.remove(email);
            return "Code vérifié avec succès !";
        }

        return "Code invalide.";
    }

    // Classe interne pour stocker le code et sa date d'expiration
    private static class VerificationData {
        private String code;
        private LocalDateTime expirationTime;

        public VerificationData(String code, LocalDateTime expirationTime) {
            this.code = code;
            this.expirationTime = expirationTime;
        }

        public String getCode() {
            return code;
        }

        public LocalDateTime getExpirationTime() {
            return expirationTime;
        }
    }
}
