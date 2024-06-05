package org.eduardomaravill.gestionclientes.services;

import jakarta.transaction.Transactional;
import org.eduardomaravill.gestionclientes.dto.SearchCriteriaClientDto;
import org.eduardomaravill.gestionclientes.models.Client;
import org.eduardomaravill.gestionclientes.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements IClientService{

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void registerClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void updateClient(Long id, Client client) {
        client.setId(id);
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> searchClient(SearchCriteriaClientDto clientCriteria) {
        return clientRepository.searchClients(clientCriteria.getFirstName(), clientCriteria.getEmail(), clientCriteria.getPhone());
    }
}
