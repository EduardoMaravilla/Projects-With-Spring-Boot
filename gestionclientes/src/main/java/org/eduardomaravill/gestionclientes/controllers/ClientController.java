package org.eduardomaravill.gestionclientes.controllers;


import org.eduardomaravill.gestionclientes.dto.EmailDto;
import org.eduardomaravill.gestionclientes.dto.SearchCriteriaClientDto;
import org.eduardomaravill.gestionclientes.models.Client;
import org.eduardomaravill.gestionclientes.services.IClientService;
import org.eduardomaravill.gestionclientes.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IClientService clientService;

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.getClient(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody Client client) {
        clientService.updateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/client")
    public ResponseEntity<Void> registerClient(@RequestBody Client client) {
        clientService.registerClient(client);
        emailService.sendEmail(client);


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Client>> searchClient(@RequestBody SearchCriteriaClientDto clientDto) {
        List<Client> clients = clientService.searchClient(clientDto);
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
