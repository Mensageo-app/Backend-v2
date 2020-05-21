package com.mensageo.app.config;

import com.sendgrid.SendGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendGridConfiguration {

    @Bean
    SendGrid createSendGridService()  {
        return new SendGrid(System.getenv("SENDGRID_API_KEY"));
    }
}
