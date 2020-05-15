package com.mensageo.app.services;

import org.junit.Ignore;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class GmailClientTest {

    @Ignore
    @Test
    public void sendEmail() throws GeneralSecurityException, IOException, MessagingException {
        // This test shouldn't run in the pipeline
        // it just tests if emails can be sent via gmail
        GmailClient gmailClient = new GmailClient();

        EmailContent emailContent = new EmailContent();
        emailContent.setCompany("Company name");
        emailContent.setSubject("Email subject");
        emailContent.setBody("Email body 1");

        gmailClient.sendEmail(emailContent);


    }
}