package com.mensageo.app;

import com.mensageo.app.model.Product;
import com.mensageo.app.repository.ProductRepository;
import com.sendgrid.SendGrid;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {
    static String API_ROOT = "/api/products";

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private SendGrid sendGridServiceMock;

    private MockMvc mvc;

    @Autowired
    private ProductRepository productRepository;

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

//    @Test
//    public void shouldReturn201WhenCreatingAValidProduct() throws Exception{
//        Product newProduct = new Product();
//        newProduct.setName("TestProductName");
//        newProduct.setIcon("TestIconPath");
//        newProduct.setDescription("TestProductDescription");
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        this.mvc
//                .perform(MockMvcRequestBuilders.post(API_ROOT).contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(newProduct)))
//                .andExpect(status().isCreated());
//
//    }

    @Test
    public void shouldReturn200WhenRequestProductById() throws Exception{
        Product newProduct = new Product();
        newProduct.setName("TestProduct");
        newProduct.setIcon("TestPath");
        newProduct.setDescription("TestProductDescription");
        productRepository.save(newProduct);

        String url = API_ROOT.concat("/").concat(String.valueOf(newProduct.getId()));

        this.mvc
                .perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)newProduct.getId())))
                .andExpect(jsonPath("$.name", is(newProduct.getName())));
    }
}
