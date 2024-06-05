package org.eduardomaravill.gestionclientes.services;

import org.eduardomaravill.gestionclientes.dto.SearchCriteriaClientDto;
import org.eduardomaravill.gestionclientes.models.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClients();
    Client getClient(Long id);
    void registerClient(Client client);
    void updateClient(Long id, Client client);
    void deleteClient(Long id);
    List<Client> searchClient(SearchCriteriaClientDto clientDto);
}
