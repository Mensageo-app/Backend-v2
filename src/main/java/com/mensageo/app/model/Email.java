package com.mensageo.app.model;

import javax.persistence.*;

@Entity
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private HospitalNeeds hospitalNeeds;

    private long quantity;
    private String name;
    private String company;
    private String phoneNumber;
    private String description;

    public long getId() {
        return id;
    }


    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HospitalNeeds getHospitalNeeds() {
        return hospitalNeeds;
    }

    public void setHospitalNeeds(HospitalNeeds hospitalNeeds) {
        this.hospitalNeeds = hospitalNeeds;
    }
}
