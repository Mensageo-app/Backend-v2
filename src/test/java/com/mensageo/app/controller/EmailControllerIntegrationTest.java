package com.mensageo.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensageo.app.model.Email;
import com.mensageo.app.model.Hospital;
import com.mensageo.app.model.Product;
import com.mensageo.app.repository.EmailRepository;
import com.mensageo.app.repository.ProductRepository;
import com.mensageo.app.services.EmailContent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
public class EmailControllerIntegrationTest {
    static String API_ROOT = "/api/emails";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void initTest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        productRepository.deleteAll();
        emailRepository.deleteAll();

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
    }

    @Test
    public void shouldSaveOneRecordInTheEmailTable() throws Exception {
        // Given
        EmailContent emailContent = createEmailContent();
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product();
        product.setName("test");
        productRepository.save(product);


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

    private EmailContent createEmailContent() {
        EmailContent emailContent = new EmailContent();
        emailContent.setBody("Email body");
        emailContent.setSubject("Email subject");
        emailContent.setProductId(1L);
        emailContent.setName("Name description");
        emailContent.setCompany("Company description");
        emailContent.setPhoneNumber("+5555-5555");
        emailContent.setDescription("Offer description");
        emailContent.setQuantity(100L);
        return emailContent;
    }
}