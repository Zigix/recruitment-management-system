package com.example.recruitmentanagementsystem.service;

import com.example.recruitmentanagementsystem.domain.model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(notificationEmail.getFrom());
            messageHelper.setTo(notificationEmail.getTo());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getText());
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");
        } catch (MailException e) {
            log.error("Error occurred when trying to send email");
        }
    }
}
