package io.teamchallenge.service.impl;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Service
public class MailService {

    private static final String NEW_PASSWORD_SUBJECT = "Your GadgetHouse password reset request from GadgetHouse";

    private Resend resend;
    private SpringTemplateEngine templateEngine;

    @Value("${RESEND_FROM_EMAIL}")
    private String fromEmail;

    @Value("${frontend.server.url}")
    private String frontendServerUrl;

    public MailService(
            @Value("${RESEND_API_KEY}") String apiKey,
            SpringTemplateEngine templateEngine,
            @Value("${frontend.server.url}") String frontendServerUrl
    ) {
        this.resend = new Resend(apiKey);
        this.templateEngine = templateEngine;
        this.frontendServerUrl = frontendServerUrl;
    }

    @Async
    public void sendResetPasswordEmail(String email, String recoveryLink) {
        log.debug("Start method sendResetPasswordEmail from MailService");

        Context context = new Context();
        log.debug("Context created");

        context.setVariable("recoveryLink", recoveryLink);
        log.debug("Variable recoveryLink is set");

        String htmlContent = templateEngine.process("password_recovery_email_template", context);
        log.debug("HTML template rendered");


        try {
            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from(fromEmail)
                    .to(email)
                    .subject(NEW_PASSWORD_SUBJECT)
                    .html(htmlContent)
                    .build();

            CreateEmailResponse response = resend.emails().send(params);
            log.info("Reset password email sent. Resend message id: {}", response.getId());

        } catch (Exception e) {
            log.error("Failed to send reset password email", e);
            throw new RuntimeException("Failed to send email", e);
        }
        log.debug("method sendResetPasswordEmail from MailService completed");
    }
}
