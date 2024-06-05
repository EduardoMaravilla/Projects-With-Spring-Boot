package org.eduardomaravill.gestionclientes.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.eduardomaravill.gestionclientes.dto.EmailDto;
import org.eduardomaravill.gestionclientes.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class EmailServiceImpl implements IEmailService{

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(EmailDto email) {

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.office365.com");

        String username = "eduardomaravilladev@hotmail.com";
        String password = "#vh`;C2oo8+jd8Aog%#q";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo().getFirst()));
            message.setSubject(email.getSubject());
            message.setContent(email.getBody(),"text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Sent email correctly");

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void sendEmail(Client client) {
        String bodyMessage = loadHtmlBody(client);
        EmailDto emailDto = new EmailDto();
        emailDto.setBody(bodyMessage);
        emailDto.setFrom("eduardomaravilladev@hotmail.com");
        List<String> emailsTo=new ArrayList<>();
        emailsTo.add(client.getEmail());
        emailDto.setTo(emailsTo);
        emailDto.setSubject("Welcome new client");
        sendEmail(emailDto);
    }

    private String loadHtmlBody(Client client){
        Context context = new Context();
        context.setVariable("firstName", client.getFirstName());
        return templateEngine.process("email.html",context);
    }


}
