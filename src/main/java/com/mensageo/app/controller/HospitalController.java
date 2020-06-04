package com.mensageo.app.controller;

import com.mensageo.app.model.Hospital;
import com.mensageo.app.repository.HospitalRepository;
import com.mensageo.app.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping()
    public Iterable findAll() {
        return hospitalService.findAll();
    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
    public Hospital create(@RequestBody Hospital hospital) {
        return hospitalRepository.save(hospital);
    }


}
