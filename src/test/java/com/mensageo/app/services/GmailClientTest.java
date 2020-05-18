package com.mensageo.app.services;

import com.google.api.services.gmail.Gmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmailClientTest {

    @MockBean
    private Gmail gmailServiceMock;

    @Mock
    private Gmail.Users usersMock;

    @Mock
    private Gmail.Users.Messages messagesMock;

    @Mock
    private Gmail.Users.Messages.Send sendMock;

    @Autowired
    private GmailClient gmailClient;

    @Test
    public void sendEmail() throws IOException, MessagingException {

        when(gmailServiceMock.users()).thenReturn(usersMock);
        when(usersMock.messages()).thenReturn(messagesMock);
        when(messagesMock.send(anyString(), any(com.google.api.services.gmail.model.Message.class))).thenReturn(sendMock);

        EmailContent emailContent = new EmailContent();
        emailContent.setCompany("Company name");
        emailContent.setSubject("Email subject");
        emailContent.setBody("Email body 1");

        gmailClient.sendEmail(emailContent);

        verify(messagesMock).send(eq("mensageo.backend@gmail.com"), any(com.google.api.services.gmail.model.Message.class));

    }

    private class Message {
    }
}