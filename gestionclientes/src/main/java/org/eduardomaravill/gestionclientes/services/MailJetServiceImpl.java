package org.eduardomaravill.gestionclientes.services;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.eduardomaravill.gestionclientes.dto.EmailDto;
import org.eduardomaravill.gestionclientes.models.Client;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service("JetMailService")
public class MailJetServiceImpl implements  IEmailService{

    @Autowired
    protected TemplateEngine templateEngine;

    @Override
    public void sendEmail(EmailDto emailDto){
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient("2a717b7e1ec32b1924bb54d1d4ba0809", "a3d534af2dd5fff53664fb6c08ca4dfa");
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", emailDto.getFrom())
                                        .put("Name", "Company Eduardo Dev"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", emailDto.getTo().getFirst())
                                                .put("Name", emailDto.getName())))
                                .put(Emailv31.Message.SUBJECT, emailDto.getSubject())
                                .put(Emailv31.Message.TEXTPART, "Greetings from Mailjet!")
                                .put(Emailv31.Message.HTMLPART, emailDto.getBody())));
        try {
            response = client.post(request);
        } catch (MailjetException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.getStatus());
        System.out.println(response.getData());
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
