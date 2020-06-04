package com.mensageo.app.services;

import com.mensageo.app.controller.dto.HospitalResponse;
import com.mensageo.app.model.Hospital;
import com.mensageo.app.repository.HospitalRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;


@Component
public class HospitalService {

        private HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Iterable<HospitalResponse> findAll(){
        Iterator<Hospital> hospitalEntity = hospitalRepository.findAll().iterator();
        ArrayList<HospitalResponse> hospitalResponseList = new ArrayList<>();

        while (hospitalEntity.hasNext()) {
            HospitalResponse responseIter = new HospitalResponse(hospitalEntity.next());
            hospitalResponseList.add(responseIter);  }
            Iterable<HospitalResponse> iterable = hospitalResponseList;
            return iterable;
        }
        };




