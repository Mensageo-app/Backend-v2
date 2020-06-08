package com.mensageo.app.controller;

import com.mensageo.app.controller.dto.EmailRequest;
import com.mensageo.app.services.EmailContent;
import com.mensageo.app.services.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private MailerService mailerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody EmailRequest emailRequest) {

        try {
            EmailContent emailContent = new EmailContent(emailRequest);
            mailerService.sendEmail(emailContent);

        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "The email couldn't be sent", exc);
        }
    }
}
