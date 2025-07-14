package dislog.cs.cs.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, VerificationData> verificationStorage = new HashMap<>();

    public String sendVerificationCode(String email) {
        // Générer un code aléatoire de 6 chiffres
        String code = String.format("%06d", new Random().nextInt(999999));

        // Sauvegarder le code et son expiration (10 secondes)
        verificationStorage.put(email, new VerificationData(code, LocalDateTime.now().plusSeconds(30)));

        // Envoyer l'e-mail
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Votre code de vérification");
        message.setText("Votre code de vérification est : " + code);
        message.setFrom("qasidkarim04@gmail.com");
        mailSender.send(message);

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
