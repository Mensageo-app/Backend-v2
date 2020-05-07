package com.mensageo.app;

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
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
public class HospitalNeedsTest {
    static String API_ROOT = "/api/hospitalsneeds";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void initTest() {
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void ShouldReturn404WhenRequestTheNeedsOfHospitalThatNotExists() throws Exception
    {
        String idHospital = "-1";

        this.mvc
                .perform(MockMvcRequestBuilders.get(API_ROOT.concat("/").concat(idHospital)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}
