package com.mensageo.app.services;

import com.mensageo.app.model.Hospital;
import com.mensageo.app.model.HospitalNeeds;
import com.mensageo.app.model.Product;
import com.mensageo.app.repository.EmailRepository;
import com.mensageo.app.repository.HospitalNeedsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.hamcrest.MockitoHamcrest;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MailerServiceTest {

    @Mock
    MailerClient sendGridClientMock;

    @Mock
    EmailRepository emailRepository;

    @Mock
    HospitalNeedsRepository hospitalNeedsRepository;

    @Mock
    Logger log;

    MailerService mailerService;

    @Mock
    HospitalNeeds hospitalNeedsMock;

    @Before
    public void setUp() {
        when(hospitalNeedsRepository.findById(anyLong())).thenReturn(Optional.of(hospitalNeedsMock));
        Hospital hospitalMock = new Hospital();
        hospitalMock.setName("Hospital");
        hospitalMock.setEmail("a@a.s");
        Product productMock = new Product();
        productMock.setName("Product Name");
        when(hospitalNeedsMock.getHospital()).thenReturn(hospitalMock);
        when(hospitalNeedsMock.getProduct()).thenReturn(productMock);
        lenient().when(hospitalNeedsMock.getId()).thenReturn(5L);


        mailerService = new MailerService(sendGridClientMock, emailRepository, hospitalNeedsRepository, log);
    }

    @Test
    public void shouldSendAnEmailAndSaveItOnDatabaseWhenEmailContentIsValid() throws IOException, MessagingException, GeneralSecurityException {
        // Given
        EmailContent emailContent = new EmailContent();

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(sendGridClientMock).sendEmail(emailContent, "a@a.s");
        verify(emailRepository).save(any());
    }

    @Test
    public void shouldSaveEmailWithAllProperties() throws GeneralSecurityException, IOException, MessagingException {
        // Given
        EmailContent emailContent = createEmailContentWithRequestData();

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(emailRepository).save(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("hospitalNeeds", equalTo(hospitalNeedsMock)),
                        hasProperty("name", equalTo("Name description")),
                        hasProperty("phoneNumber", equalTo("+5555-5555")),
                        hasProperty("description", equalTo("Offer description")),
                        hasProperty("quantity", equalTo(100L))
                )
        ));
    }


    @Test
    public void shouldSendEmailWithAllRequiredPropertiesToCorrectDestination() throws GeneralSecurityException, IOException, MessagingException {
        // Given
        EmailContent emailContent = createEmailContentWithRequestData();

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(sendGridClientMock).sendEmail(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("name", equalTo("Name description")),
                        hasProperty("phoneNumber", equalTo("+5555-5555")),
                        hasProperty("description", equalTo("Offer description")),
                        hasProperty("quantity", equalTo(100L)),
                        hasProperty("hospitalNeedId", equalTo(5L)),
                        hasProperty("productName", equalTo("Product Name")),
                        hasProperty("hospitalName", equalTo("Hospital")),
                        hasProperty("email", equalTo("la@gmail.com")),
                        hasProperty("company", equalTo(null)),
                        hasProperty("additionalEmail", equalTo(null)),
                        hasProperty("additionalPhoneNumber", equalTo(null))

                )
        ), eq("a@a.s"));
    }

    @Test(expected=RuntimeException.class)
    public void shouldLogErrorIfSavingOnDatabaseFails() throws GeneralSecurityException, IOException, MessagingException {
        // Given
        EmailContent emailContent = new EmailContent();

        when(emailRepository.save(any())).thenThrow(RuntimeException.class);

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(log).error(any(String.class), any(RuntimeException.class));
    }


    @Test(expected=RuntimeException.class)
    public void shouldLogErrorIfSendingEmailFails() throws IOException, MessagingException, GeneralSecurityException {
        // Given
        EmailContent emailContent = new EmailContent();

        doThrow(RuntimeException.class)
                .when(sendGridClientMock)
                .sendEmail(any(EmailContent.class), anyString());

        // When
        mailerService.sendEmail(emailContent);

        // Then
        verify(log).error(any(String.class), any(RuntimeException.class));
    }


    private EmailContent createEmailContentWithRequestData() {

        EmailContent emailContent = new EmailContent();
        emailContent.setHospitalNeedId(5L);
        emailContent.setName("Name description");
        emailContent.setPhoneNumber("+5555-5555");
        emailContent.setDescription("Offer description");
        emailContent.setQuantity(100L);
        emailContent.setEmail("la@gmail.com");

        return emailContent;
    }

}