package com.mensageo.app.controller;

import com.mensageo.app.services.EmailContent;
import com.mensageo.app.services.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private MailerService mailerService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody EmailContent emailContent) {
        mailerService.sendEmail(emailContent);
    }
}
