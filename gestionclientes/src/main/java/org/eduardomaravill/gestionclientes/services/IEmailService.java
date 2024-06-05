package org.eduardomaravill.gestionclientes.services;

import org.eduardomaravill.gestionclientes.dto.EmailDto;
import org.eduardomaravill.gestionclientes.models.Client;

public interface IEmailService {
    void  sendEmail(EmailDto email);
    void  sendEmail(Client client);
}
