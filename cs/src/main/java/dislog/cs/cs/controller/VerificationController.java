package dislog.cs.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dislog.cs.cs.service.VerificationService;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/admin/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @GetMapping("/send-code")
    public String sendVerificationCode(@RequestParam String email) throws MessagingException {
        return verificationService.sendVerificationCode(email);
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        return verificationService.verifyCode(email, code);
    }
}
