package com.mensageo.app.services;

import com.mensageo.app.controller.dto.HospitalResponse;
import com.mensageo.app.model.Hospital;
import com.mensageo.app.repository.HospitalRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HospitalServiceTest {

    @MockBean
    HospitalRepository hospitalRepository;

    HospitalService hospitalService;

    Hospital hospital;

    @Before
    public void setUp() {
        hospital = new Hospital();
        hospital.setEmail("test@gmail.com");
        hospital.setName("Hospital Mar");
        hospital.setAddress("Calle 1");
        hospital.setPhoneNumber("123456789");

        Iterable<Hospital> hospitals = Arrays.asList(hospital);

        when(hospitalRepository.findAll()).thenReturn(hospitals);
        hospitalService =  new HospitalService(hospitalRepository);
    }

    @Test
    public void dtoShouldHaveNameAdressHospitalId() {
        HospitalResponse hospitalResponse = hospitalService.findAll().iterator().next();

        verify(hospitalRepository).findAll();

        assertEquals(hospitalResponse.getName(), hospital.getName());
        assertEquals(hospitalResponse.getAddress(), hospital.getAddress());
        assertEquals(hospitalResponse.getId(), hospital.getId());

    }


}
