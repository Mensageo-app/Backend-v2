package com.mensageo.app.controller;

import com.mensageo.app.repository.HospitalNeedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/hospitalsneeds")
public class HospitalNeedsController {

    @Autowired
    private HospitalNeedsRepository hospitalNeedsRepository;

    @GetMapping()
    public Iterable findAll() {
        return hospitalNeedsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Iterable findByHospital(@PathVariable long id) {
        if( hospitalNeedsRepository.existsById(id)){
            return hospitalNeedsRepository.findByHospitalId(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

}
