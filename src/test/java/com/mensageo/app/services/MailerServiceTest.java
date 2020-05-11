package com.mensageo.app.services;


import com.mensageo.app.repository.EmailRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MailerServiceTest {

    @Mock
    MailerClient mailerClient;

    @Mock
    EmailRepository emailRepository;

    @Test
    public void shouldSendAnEmailAndSaveItOnDatabaseWhenEmailContentIsValid(){
        // Given
        MailerService mailerService = new MailerService(mailerClient, emailRepository);
        EmailContent emailContent = new EmailContent();

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(mailerClient).sendEmail(emailContent);
        verify(emailRepository).save(any());
    }

}