package com.mensageo.app.services;

import com.mensageo.app.controller.dto.EmailRequest;
import com.mensageo.app.model.Email;

public class EmailContent {
    private String subject;
    private String body;
    private String name;
    private String phoneNumber;
    private String company;
    private String description;
    private long quantity;
    private long hospitalNeedId;

    public EmailContent() {}

    public EmailContent(EmailRequest emailRequest) {
        setSubject("We need to define a subject");
        setBody("We need to define a body");
        setName(emailRequest.getName());
        setPhoneNumber(emailRequest.getPhoneNumber());
        setCompany(emailRequest.getCompany());
        setDescription(emailRequest.getDonationInfo());
        setQuantity(emailRequest.getQuantity());
        setHospitalNeedId(emailRequest.getHospitalNeedId());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
