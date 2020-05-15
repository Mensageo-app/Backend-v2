package com.mensageo.app.services;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;

public interface MailerClient {
    void sendEmail(EmailContent emailContent) throws MessagingException, IOException, GeneralSecurityException;
}
