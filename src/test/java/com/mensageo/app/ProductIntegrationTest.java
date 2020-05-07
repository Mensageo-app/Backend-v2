package com.mensageo.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensageo.app.model.Hospital;
import com.mensageo.app.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    static String API_ROOT = "/api/products";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void initTest() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldReturn200WhenRequestListOfProducts() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders.get(API_ROOT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn201WhenCreatingAValidProduct() throws Exception{
        Product newProduct = new Product();
        newProduct.setName("TestProductName");
        newProduct.setIcon("TestIconPath");
        newProduct.setDescription("TestProductDescription");

        ObjectMapper mapper = new ObjectMapper();

        this.mvc
                .perform(MockMvcRequestBuilders.post(API_ROOT).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newProduct)))
                .andExpect(status().isCreated());

    }
}
