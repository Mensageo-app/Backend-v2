package com.mensageo.app.services;

import com.mensageo.app.model.Email;
import com.mensageo.app.repository.EmailRepository;
import org.springframework.stereotype.Component;

@Component
public class MailerService {
    private MailerClient mailerClient;
    private EmailRepository emailRepository;

    public MailerService(MailerClient mailerClient, EmailRepository emailRepository) {

        this.mailerClient = mailerClient;
        this.emailRepository = emailRepository;
    }

    public void sendEmail(EmailContent emailContent) {
        mailerClient.sendEmail(emailContent);
        emailRepository.save(new Email());

    }
}
