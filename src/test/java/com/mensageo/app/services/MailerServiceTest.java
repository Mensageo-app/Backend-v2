package com.mensageo.app.services;

import com.mensageo.app.repository.EmailRepository;
import groovy.util.logging.Log4j;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        emailContent.setMakerId(1L);
        emailContent.setHospitalNeedsId(5L);

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(emailRepository).save(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("makerId", equalTo(1L)),
                        hasProperty("hospitalNeedsId", equalTo(5L)),
                        hasProperty("subject", equalTo("Email subject")),
                        hasProperty("body", equalTo("Email body"))
                )
        ));
    }

    @Test
    public void shouldLogErrorIfSavingOnDatabaseFails(){
        // Given
        EmailContent emailContent = new EmailContent();
        emailContent.setMakerId(1L);
        emailContent.setHospitalNeedsId(5L);

        when(emailRepository.save(any())).thenThrow(RuntimeException.class);

        // When
        mailerService.sendEmail(emailContent);

        verify(log).error(any(String.class), any(RuntimeException.class));
    }

}