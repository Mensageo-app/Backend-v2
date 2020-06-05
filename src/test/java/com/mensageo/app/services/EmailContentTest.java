package com.mensageo.app.services;


import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailContentTest {


    @Test
    public void shouldReturnCorrectSubject() {

        EmailContent emailContent = new EmailContent();
        emailContent.setProductName("Mask");
        String expectedSubject = "Someone has answered your request for product Mask";

        assertEquals(expectedSubject,emailContent.createSubject());
    }

    @Test
    public void shouldReturnCorrectBody() {

        EmailContent emailContent = new EmailContent();
        emailContent.setHospitalName("Hospital One");
        emailContent.setName("Ana");
        emailContent.setProductName("Mask");
        emailContent.setQuantity(100);
        emailContent.setPhoneNumber("123456789");
        emailContent.setEmail("ana@gmail.com");
        emailContent.setDescription("Hello");

        String expectedBody = "Hi Hospital One,\n\n" +
                "We have good news! Ana has answered your request for product Mask, to provide 100 of it. Here is their information, please contact them.\n\n" +
                "Maker and product information:\n\n" +
                "Full Name: Ana\n"+
                "Phone: 123456789\n"+
                "Email: ana@gmail.com\n"+
                "Product Amount: 100\n"+
                "Description: Hello\n"+
                "Once you have contacted the Maker and you have agreed a delivery date please inform us about it by replying to this email. In addition, we will be really" +
                " grateful if once your order has been fulfilled you contact us again. In this way we guarantee that Mensageo.com will always have accurate and updated" +
                " information and we can help more people.\n\n" +
                 "Thank you for using Mensageo!! Please spread the word about it!\n\n"+
                 "Regards,\n" +
                "Mensageo Team";

        assertEquals(expectedBody,emailContent.createBody());
    }

}
