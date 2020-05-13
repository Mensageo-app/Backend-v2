package com.mensageo.app.services;

import com.mensageo.app.repository.EmailRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailerServiceTest {

    @Mock
    MailerClient mailerClient;

    @Mock
    EmailRepository emailRepository;

    @Mock
    Logger log;

    MailerService mailerService;

    @Before
    public void setUp() {
        mailerService = new MailerService(mailerClient, emailRepository, log);
    }

    @Test
    public void shouldSendAnEmailAndSaveItOnDatabaseWhenEmailContentIsValid() {
        // Given
        EmailContent emailContent = new EmailContent();

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(mailerClient).sendEmail(emailContent);
        verify(emailRepository).save(any());
    }

    @Test
    public void shouldSaveEmailWithAllProperties() {
        // Given
        EmailContent emailContent = new EmailContent();
        emailContent.setBody("Email body");
        emailContent.setSubject("Email subject");
        emailContent.setProductId(5L);
        emailContent.setName("Name description");
        emailContent.setCompany("Company description");
        emailContent.setPhoneNumber("+5555-5555");
        emailContent.setDescription("Offer description");
        emailContent.setQuantity(100L);

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(emailRepository).save(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("productId", equalTo(5L)),
                        hasProperty("subject", equalTo("Email subject")),
                        hasProperty("body", equalTo("Email body")),
                        hasProperty("name", equalTo("Name description")),
                        hasProperty("company", equalTo("Company description")),
                        hasProperty("phoneNumber", equalTo("+5555-5555")),
                        hasProperty("description", equalTo("Offer description")),
                        hasProperty("quantity", equalTo(100L))
                )
        ));
    }

    @Test
    public void shouldLogErrorIfSavingOnDatabaseFails(){
        // Given
        EmailContent emailContent = new EmailContent();

        when(emailRepository.save(any())).thenThrow(RuntimeException.class);

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(log).error(any(String.class), any(RuntimeException.class));
    }


    @Test
    public void shouldLogErrorIfSendingEmailFails(){
        // Given
        EmailContent emailContent = new EmailContent();

        doThrow(RuntimeException.class)
                .when(mailerClient)
                .sendEmail(any(EmailContent.class));

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(log).error(any(String.class), any(RuntimeException.class));
    }

}