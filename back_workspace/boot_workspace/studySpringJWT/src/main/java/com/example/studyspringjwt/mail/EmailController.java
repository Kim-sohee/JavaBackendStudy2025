package com.example.studyspringjwt.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send/mail")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        boolean isSent = emailService.sendEmail(to, subject, text);
        return isSent ? "Mail sent successfully" : "Mail not sent";
    }
}
