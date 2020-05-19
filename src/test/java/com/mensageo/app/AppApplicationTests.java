package com.mensageo.app;

import com.google.api.services.gmail.Gmail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

	@MockBean
	private Gmail gmailServiceMock;

	@Test
	public void contextLoads() {
	}

}
