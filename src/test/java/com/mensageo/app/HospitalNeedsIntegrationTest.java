package com.mensageo.app;

import com.google.api.services.gmail.Gmail;
import com.mensageo.app.model.Hospital;
import com.mensageo.app.model.HospitalNeeds;
import com.mensageo.app.model.Product;
import com.mensageo.app.repository.HospitalNeedsRepository;
import com.mensageo.app.repository.HospitalRepository;
import com.mensageo.app.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/create_products_and_hospital.sql")
public class HospitalNeedsIntegrationTest {
    static String API_ROOT = "/api/hospital_needs";

    private Hospital hospital;
    private Product product1, product2;

    @MockBean
    private Gmail gmailServiceMock;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Autowired
    private HospitalNeedsRepository hospitalNeedsRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private ProductRepository productRepository;


    @Before
    public void initTest(){
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.hospital = hospitalRepository.findById(1L).get();
        this.product1 = productRepository.findById(1L).get();
        this.product2 = productRepository.findById(2L).get();
    }

    private void createHospitalNeed(Product product) {
        HospitalNeeds hospitalNeeds = new HospitalNeeds();
        hospitalNeeds.setHospital(hospital);
        hospitalNeeds.setProduct(product);
        hospitalNeeds.setQuantity(12);
        hospitalNeedsRepository.save(hospitalNeeds);
    }

    @Test
    public void shouldReturn404WhenRequestTheNeedsOfHospitalThatNotExists() throws Exception
    {
        String idHospital = "-1";

        this.mvc
                .perform(MockMvcRequestBuilders.get(API_ROOT.concat("/").concat(idHospital)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @Test
    public void shouldReturn200WhenRequestHospitalsNeeds() throws Exception{
        String url = API_ROOT.concat("/").concat(String.valueOf(hospital.getId()));

        this.mvc
                .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].hospital.id", is((int)hospital.getId())));


    }

    @Test
    public void shouldReturn200WhenRequestHospitalsNeedsByProduct() throws Exception{
        String url = API_ROOT.concat("/by_product/").concat(String.valueOf(product1.getId()));

        this.mvc
                .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].product.id", is(product1.getId())));
    }
}
