package com.mensageo.app.services;

import com.mensageo.app.controller.dto.EmailRequest;


public class EmailContent {

    private String name;
    private String phoneNumber;
    private String company;
    private String description;
    private long quantity;
    private long hospitalNeedId;
    private String productName;
    private String hospitalName;
    private String email;
    private String additionalEmail;
    private String additionalPhoneNumber;


    public EmailContent() {}

    public EmailContent(EmailRequest emailRequest) {
        setName(emailRequest.getName());
        setPhoneNumber(emailRequest.getPhoneNumber());
        setCompany(emailRequest.getCompany());
        setDescription(emailRequest.getDonationInfo());
        setQuantity(emailRequest.getQuantity());
        setHospitalNeedId(emailRequest.getHospitalNeedId());
        setAdditionalPhoneNumber(emailRequest.getAdditionalPhoneNumber());
        setAdditionalEmail(emailRequest.getAdditionalEmail());
        setEmail(emailRequest.getEmail());
    }

    public String createSubject() {
        return String.format("Someone has answered your request for product %s", this.productName);
    }

    public String createBody() {
        return String.format("Hi %s,\n\n" +
                "We have good news! %s has answered your request for product %s, to provide %d of it. Here is their information, please contact them.\n\n" +
                "Maker and product information:\n\n" +
                "Full Name: %s\n"+
                "Phone: %s\n"+
                "Email: %s\n"+
                "Product Amount: %d\n"+
                "Description: %s\n"+
                ( this.company != null ? "Company: "+this.company+"\n" : "") +
                ( this.additionalEmail != null ? "Additional Email: "+this.additionalEmail+"\n" : "") +
                ( this.additionalPhoneNumber != null ? "Additional Phone Number: "+this.additionalPhoneNumber+"\n" : "") +
                "Once you have contacted the Maker and you have agreed a delivery date please inform us about it by replying to this email. In addition, we will be really" +
                " grateful if once your order has been fulfilled you contact us again. In this way we guarantee that Mensageo.com will always have accurate and updated" +
                " information and we can help more people.\n\n" +
                "Thank you for using Mensageo!! Please spread the word about it!\n\n"+
                "Regards,\n" +
                "Mensageo Team", this.hospitalName, this.name,this.productName,this.quantity,this.name,this.phoneNumber,this.email,this.quantity, this.description);
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
