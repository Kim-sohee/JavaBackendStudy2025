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
        boolean isSent = emailService.sendEmail(to, subject, text, null);
        return isSent ? "Mail sent successfully" : "Mail not sent";
    }

    @PostMapping("/send/welcome")
    public String sendWelcome(@RequestParam String to) {
        String subject = "Fantry 회원가입을 환영합니다!";
        String content = "<p>회원 가입이 완료되었습니다. <br>여기에서 로그인하고 다양한 아이돌 굿즈를 거래해 보세요!</p>";
        boolean isSent = emailService.sendEmail(to, subject, content, null);
        return isSent ? "회원가입 메일이 성공적으로 발송되었습니다." : "메일 발송 실패";
    }

    @PostMapping("/send/reset-password")
    public String sendPasswordResetEmail(@RequestParam String to, @RequestParam String resetLink) {
        String subject = "비밀번호 재설정 요청";
        String content = "<p>비밀번호 재설정을 위해 아래 링크를 클릭하세요.</p>";
        boolean isSent = emailService.sendEmail(to, subject, content, resetLink);
        return isSent ? "비밀번호 재설정 메일이 성공적으로 발송되었습니다." : "메일 발송 실패";
    }
}
