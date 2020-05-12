package com.mensageo.app.services;

import com.mensageo.app.model.Email;
import com.mensageo.app.repository.EmailRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class MailerService {
    private MailerClient mailerClient;
    private EmailRepository emailRepository;
    private Logger log;

    public MailerService(MailerClient mailerClient, EmailRepository emailRepository, Logger log) {

        this.mailerClient = mailerClient;
        this.emailRepository = emailRepository;
        this.log = log;
    }

    public void sendEmail(EmailContent emailContent) {

        try {
            mailerClient.sendEmail(emailContent);

            Email email = new Email();
            email.setMakerId(emailContent.getMakerId());
            email.setHospitalNeedsId(emailContent.getHospitalNeedsId());
            email.setSubject(emailContent.getSubject());
            email.setBody(emailContent.getBody());
            emailRepository.save(email);
        } catch (Exception ex) {
            log.error("Error when saving email on database.", ex);
        }
    }
}
