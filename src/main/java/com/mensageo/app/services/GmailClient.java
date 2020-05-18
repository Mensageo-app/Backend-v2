package com.mensageo.app.services;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;


@Component
public class GmailClient implements MailerClient {

    private Gmail gmailService;

    public GmailClient(Gmail gmailService) {
        this.gmailService = gmailService;
    }

    @Override
    public void sendEmail(EmailContent emailContent) throws MessagingException, IOException {
        sendMessage("mensageo.backend@gmail.com", createEmail(
                "mensageo.backend@gmail.com",
                "no-reply@gmail.com",
                emailContent.getSubject(),
                emailContent.getBody()
        ));
    }


    private Message sendMessage(String userId,
                                MimeMessage emailContent)
            throws IOException, javax.mail.MessagingException {
        Message message = createMessageWithEmail(emailContent);
        return gmailService.users().messages().send(userId, message).execute();
    }

    /**
     * Create a MimeMessage using the parameters provided.
     *
     * @param to       email address of the receiver
     * @param from     email address of the sender, the mailbox account
     * @param subject  subject of the email
     * @param bodyText body text of the email
     * @return the MimeMessage to be used to send email
     */
    private MimeMessage createEmail(String to,
                                    String from,
                                    String subject,
                                    String bodyText)
            throws javax.mail.MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    /**
     * Create a message from an email.
     *
     * @param emailContent Email to be set to raw of message
     * @return a message containing a base64url encoded email
     * @throws IOException
     */
    private Message createMessageWithEmail(MimeMessage emailContent)
            throws IOException, javax.mail.MessagingException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }

}
