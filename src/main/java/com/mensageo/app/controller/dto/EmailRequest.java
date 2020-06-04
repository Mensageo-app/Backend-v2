package com.mensageo.app.controller.dto;

public class EmailRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private long quantity;
    private String company;
    private String additionalEmail;
    private String additionalPhoneNumber;
    private String donationInfo;
    private long hospitalNeedId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdditionalEmail() {
        return additionalEmail;
    }

    public void setAdditionalEmail(String additionalEmail) {
        this.additionalEmail = additionalEmail;
    }

    public String getAdditionalPhoneNumber() {
        return additionalPhoneNumber;
    }

    public void setAdditionalPhoneNumber(String additionalPhoneNumber) {
        this.additionalPhoneNumber = additionalPhoneNumber;
    }

    public String getDonationInfo() {
        return donationInfo;
    }

    public void setDonationInfo(String donationInfo) {
        this.donationInfo = donationInfo;
    }

    public long getHospitalNeedId() {
        return hospitalNeedId;
    }

    public void setHospitalNeedId(long hospitalNeedId) {
        this.hospitalNeedId = hospitalNeedId;
    }
}