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
    public void shouldSendEmail() throws IOException {

        when(sendGridServiceMock.api(any(Request.class))).thenReturn(mockResponse);

        EmailContent emailContent = new EmailContent();

        String mockPayload = "{\"from\":{\"email\":\"mensageo.backend@gmail.com\"}," +
                "\"subject\":\"Someone has answered your request for product null\"," +
                "\"personalizations\":[{\"to\":[{\"email\":\"b@gmail.com\"}]}]," +
                "\"content\":[{\"type\":\"text/plain\",\"value\":\"Hi null,\\n\\nWe have good news! null has answered your " +
                "request for product null, to provide 0 of it. Here is their information, please contact them.\\n\\nMaker " +
                "and product information:\\n\\nFull Name: null\\nPhone: null\\nEmail: null\\nProduct Amount: 0\\n" +
                "Description: null\\nOnce you have contacted the Maker and you have agreed a delivery date please inform " +
                "us about it by replying to this email. In addition, we will be really grateful if once your order has been " +
                "fulfilled you contact us again. In this way we guarantee that Mensageo.com will always have accurate and " +
                "updated information and we can help more people.\\n\\nThank you for using Mensageo!! Please spread the " +
                "word about it!\\n\\nRegards,\\nMensageo Team\"}]}";

        sendGridClient.sendEmail(emailContent, "b@gmail.com");

        verify(sendGridServiceMock).api(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("method", equalTo(Method.POST)),
                        hasProperty("endpoint", equalTo("mail/send")),
                        hasProperty("body", equalTo(mockPayload))
                )
        ));
    }


}