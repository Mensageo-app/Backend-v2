package com.mensageo.app;

import com.mensageo.app.model.Hospital;
import com.mensageo.app.model.HospitalNeeds;
import com.mensageo.app.repository.HospitalNeedsRepository;
import com.mensageo.app.repository.HospitalRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HospitalNeedsIntegrationTest {
    static String API_ROOT = "/api/hospital_needs";

    private static long hospitalId;
    private static boolean alreadyDbInitialized = false;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Autowired
    private HospitalNeedsRepository hospitalNeedsRepository;

    @Autowired
    private HospitalRepository hospitalRepository;


    @Before
    public void initTest(){
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        if (!alreadyDbInitialized) {
            Hospital hospital = createHospital();
            createHospitalNeed(hospital.getId(),3);
            createHospitalNeed(hospital.getId(),5);
            hospitalId = hospital.getId();
            alreadyDbInitialized = true;
        }

    }

    private void createHospitalNeed(long hospitalId, long productID) {
        HospitalNeeds hospitalNeeds = new HospitalNeeds();
        hospitalNeeds.setHospitalId(hospitalId);
        hospitalNeeds.setProductId(productID);
        hospitalNeeds.setQuantity(12);
        hospitalNeedsRepository.save(hospitalNeeds);
    }

    private Hospital createHospital() {
        Hospital hospital = new Hospital();
        hospital.setName("Test Hospital");
        hospital.setAddress("Calle 123");
        hospital.setEmail("hospital@gmail.com");
        hospitalRepository.save(hospital);
        return hospital;
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
        String url = API_ROOT.concat("/").concat(String.valueOf(hospitalId));

        this.mvc
                .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].hospitalId", is((int)hospitalId)));


    }

    @Test
    public void shouldReturn200WhenRequestHospitalsNeedsByProduct() throws Exception{
        String url = API_ROOT.concat("/by_product/").concat(String.valueOf(3));

        this.mvc
                .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].productId", is(3)));
    }
}
