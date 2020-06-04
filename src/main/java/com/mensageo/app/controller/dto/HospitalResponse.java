package com.mensageo.app.controller.dto;

import com.mensageo.app.model.Hospital;

public class HospitalResponse {

    public HospitalResponse(Hospital hospital){
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress();
    }

    private long id;

    private String name;

    private String address;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(long id){ this.id = id;
    }




}
