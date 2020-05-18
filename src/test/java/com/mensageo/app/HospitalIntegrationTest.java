package com.mensageo.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mensageo.app.model.Hospital;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HospitalIntegrationTest {
    static String API_ROOT = "/api/hospitals";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void initTest() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldReturn200WhenRequestListOfHospitals() throws Exception {
        this.mvc
                .perform(MockMvcRequestBuilders.get(API_ROOT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn201WhenCreatingAValidHospital() throws Exception{
        Hospital newHospital = new Hospital();
        newHospital.setName("TestHospital");
        newHospital.setAddress("TestAddress");

        ObjectMapper mapper = new ObjectMapper();

        this.mvc
                .perform(MockMvcRequestBuilders.post(API_ROOT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newHospital)))
                .andExpect(status().isCreated());

    }
}
