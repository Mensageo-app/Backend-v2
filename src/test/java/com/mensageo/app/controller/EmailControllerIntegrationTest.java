package com.mensageo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.gmail.Gmail;
import com.mensageo.app.model.Email;
import com.mensageo.app.repository.EmailRepository;
import com.mensageo.app.services.EmailContent;
import com.mensageo.app.services.MailerClient;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/delete_emails_and_load_one_product.sql")
public class EmailControllerIntegrationTest {
    static String API_ROOT = "/api/emails";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private Gmail gmailServiceMock;

    @Autowired
    private EmailRepository emailRepository;

    @MockBean
    private MailerClient mockMailerClient;

    @Before
    public void initTest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldReturn201WhenRequestCreateEmail() throws Exception {
        // Given
        EmailContent emailContent = createEmailContent();
        ObjectMapper mapper = new ObjectMapper();

        // Then
        this.mockMvc
                .perform(MockMvcRequestBuilders.post(API_ROOT.concat("/create"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(emailContent))
                )
                .andExpect(status().isCreated());
        verify(mockMailerClient).sendEmail(MockitoHamcrest.argThat(
                allOf(
                        hasProperty("hospitalNeedId", equalTo(emailContent.getHospitalNeedId())),
                        hasProperty("subject", equalTo(emailContent.getSubject())),
                        hasProperty("body", equalTo(emailContent.getBody())),
                        hasProperty("name", equalTo(emailContent.getName())),
                        hasProperty("company", equalTo(emailContent.getCompany())),
                        hasProperty("phoneNumber", equalTo(emailContent.getPhoneNumber())),
                        hasProperty("description", equalTo(emailContent.getDescription())),
                        hasProperty("quantity", equalTo(emailContent.getQuantity()))
                )
        ));
    }

    @Test
    public void shouldSaveOneRecordInTheEmailTable() throws Exception {
        // Given
        EmailContent emailContent = createEmailContent();
        ObjectMapper mapper = new ObjectMapper();

        // When
        this.mockMvc
                .perform(MockMvcRequestBuilders.post(API_ROOT.concat("/create"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(emailContent))
                );

        // Then
        assertEquals(1, emailRepository.count());
        Email email = emailRepository.findAll().iterator().next();
        assertEquals("Name description", email.getName());
        assertEquals("Email body", email.getBody());
        assertEquals("Email subject", email.getSubject());
        assertEquals(100L, email.getQuantity());

    }

    @Test
    public void shouldReturnAnError500() throws Exception {

        // Given
        EmailContent emailContent = createEmailContent();
        ObjectMapper mapper = new ObjectMapper();

        doThrow(new RuntimeException()).when(mockMailerClient).sendEmail(any(EmailContent.class));

        // When
        MvcResult response = this.mockMvc
                .perform(MockMvcRequestBuilders.post(API_ROOT.concat("/create"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(emailContent)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is5xxServerError())
                .andReturn();
                //.andExpect(jsonPath("$.message", Matchers.is("The email couldn't be sent")));

        String content=response.getResponse().getContentAsString();
        System.out.println("Content: "+content);

    }

    private EmailContent createEmailContent() {
        EmailContent emailContent = new EmailContent();
        emailContent.setBody("Email body");
        emailContent.setSubject("Email subject");
        emailContent.setHospitalNeedId(1L);
        emailContent.setName("Name description");
        emailContent.setCompany("Company description");
        emailContent.setPhoneNumber("+5555-5555");
        emailContent.setDescription("Offer description");
        emailContent.setQuantity(100L);
        return emailContent;
    }
}