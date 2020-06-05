package com.mensageo.app.services;

import com.mensageo.app.controller.dto.EmailRequest;

public class EmailContent {
    private String name;
    private String phoneNumber;
    private String company;
    private String description;
    private long quantity;
    private long hospitalNeedId;

    public EmailContent() {}

    public EmailContent(EmailRequest emailRequest) {
        setName(emailRequest.getName());
        setPhoneNumber(emailRequest.getPhoneNumber());
        setCompany(emailRequest.getCompany());
        setDescription(emailRequest.getDonationInfo());
        setQuantity(emailRequest.getQuantity());
        setHospitalNeedId(emailRequest.getHospitalNeedId());
    }

    public String createSubject() {
        return "";
    }

    public String createBody() {
        return "";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getHospitalNeedId() {
        return hospitalNeedId;
    }

    public void setHospitalNeedId(long hospitalNeedId) {
        this.hospitalNeedId = hospitalNeedId;
    }
}
