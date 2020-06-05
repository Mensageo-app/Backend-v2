package com.mensageo.app.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendGridClientTest {

    @Mock
    private Response mockResponse;

    @MockBean
    private SendGrid sendGridServiceMock;

    @Autowired
    private SendGridClient sendGridClient;

    @Test
    public void sendEmail() throws IOException {

        when(sendGridServiceMock.api(any(Request.class))).thenReturn(mockResponse);
        EmailContent emailContent = new EmailContent();
        String body = emailContent.createBody();
        String subject = emailContent.createSubject();

        String mockPayload = "{\"from\":{\"email\":\"mensageo.backend@gmail.com\"},\"subject\":\""+
                subject+"\",\"personalizations\":[{\"to\":[{\"email\":\"mensageo.backend@gmail.com\"}]}]," +
                "\"content\":[{\"type\":\"text/plain\",\"value\":\""+body+"\"}]}";


        sendGridClient.sendEmail(emailContent);

        verify(sendGridServiceMock).api(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("method", equalTo(Method.POST)),
                        hasProperty("endpoint", equalTo("mail/send")),
                        hasProperty("body", equalTo(mockPayload))
                )
        ));

    }


}