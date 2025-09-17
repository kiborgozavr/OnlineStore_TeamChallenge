package io.teamchallenge.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class MailService {

    private final String NEW_PASSWORD_SUBJECT = "Your GadgetHouse password reset request from GadgetHouse";

    private final JavaMailSender mailSender;
    private final MessageSource messages;

    @Value("${spring.mail.username}")
    private String supportEmail;
    @Value("${frontend.server.url}")
    private String frontendServerUrl;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendResetPasswordEmail(String email, String recoveryLink) throws MessagingException {
        Context context = new Context();
        context.setVariable("recoveryLink", recoveryLink);
        String htmlContent = templateEngine.process("password_recovery_email_template", context);
        mailSender.send(constructEmail(NEW_PASSWORD_SUBJECT, htmlContent, email));
    }

    private MimeMessage constructEmail(String subject, String body, String email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(body, true);
        return message;
    }
}
