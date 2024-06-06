package org.eduardomaravill.gestionclientes.controllers;

import org.eduardomaravill.gestionclientes.dto.EmailDto;
import org.eduardomaravill.gestionclientes.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    @Qualifier("JetMailService")
    IEmailService emailService;


    @GetMapping("/emailSend")
    public ResponseEntity<Void> sendEmail(){
        EmailDto emailDto = new EmailDto();
        emailDto.setFrom("eduardomaravilladev@hotmail.com");
        emailDto.setName("EduardoDev");
        List<String> emailsTo = new ArrayList<>();
        emailsTo.add("eduardomaravilla@hotmail.com");
        emailDto.setTo(emailsTo);
        emailDto.setSubject("Prueba de envío de email");
        emailDto.setBody("Esta es una prueba de envío de emails");
        emailService.sendEmail(emailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
