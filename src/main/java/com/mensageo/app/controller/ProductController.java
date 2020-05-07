package com.mensageo.app.controller;

import com.mensageo.app.model.Hospital;
import com.mensageo.app.model.Product;
import com.mensageo.app.repository.HospitalRepository;
import com.mensageo.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public Iterable findAll() {
        return productRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
