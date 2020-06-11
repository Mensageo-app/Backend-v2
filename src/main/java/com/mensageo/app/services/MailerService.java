package com.mensageo.app.services;

import com.mensageo.app.model.Email;
import com.mensageo.app.repository.EmailRepository;
import com.mensageo.app.repository.HospitalNeedsRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
public class MailerService {
    private MailerClient mailerClient;
    private EmailRepository emailRepository;
    private HospitalNeedsRepository hospitalNeedsRepository;
    private Logger log;

    public MailerService(MailerClient mailerClient, EmailRepository emailRepository, HospitalNeedsRepository hospitalNeedsRepository, Logger log) {

        this.mailerClient = mailerClient;
        this.emailRepository = emailRepository;
        this.hospitalNeedsRepository = hospitalNeedsRepository;
        this.log = log;
    }

    public void sendEmail(EmailContent emailContent) throws GeneralSecurityException, MessagingException, IOException {
        try {
            Email email = new Email();
            email.setHospitalNeeds(hospitalNeedsRepository.findById(emailContent.getHospitalNeedId()).get());
            email.setName(emailContent.getName());
            email.setCompany(emailContent.getCompany());
            email.setPhoneNumber(emailContent.getPhoneNumber());
            email.setDescription(emailContent.getDescription());
            email.setQuantity(emailContent.getQuantity());

            emailContent.setHospitalName(email.getHospitalNeeds().getHospital().getName());
            emailContent.setProductName(email.getHospitalNeeds().getProduct().getName());
            emailRepository.save(email);
            mailerClient.sendEmail(emailContent,email.getHospitalNeeds().getHospital().getEmail());

        } catch (Exception ex) {
            log.error("Error when saving email on database.", ex);
            throw ex;
        }
    }
}
