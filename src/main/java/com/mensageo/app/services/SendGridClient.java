package com.mensageo.app.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SendGridClient implements MailerClient {

    private SendGrid sendGridService;

    public SendGridClient(SendGrid sengridService) {
        this.sendGridService = sengridService;
    }

    @Override
    public void sendEmail(EmailContent emailContent, String destination) throws IOException {
        Email from = new Email("mensageo.backend@gmail.com");
        String subject = emailContent.createSubject();
        Email to = new Email(destination);
        Content content = new Content("text/plain", emailContent.createBody());
        Mail mail = new Mail(from, subject, to, content);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridService.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
