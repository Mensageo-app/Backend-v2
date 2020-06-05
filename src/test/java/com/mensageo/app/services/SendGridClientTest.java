package com.mensageo.app.services;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

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

        sendGridClient.sendEmail(emailContent);

        verify(sendGridServiceMock).api(any(Request.class));

    }


}