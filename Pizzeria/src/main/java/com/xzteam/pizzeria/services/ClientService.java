package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ClientService {
    private static final Logger log = Logger.getLogger(ClientService.class.getName());

    @Autowired
    ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findOne(id);
    }

    public Client addClient(Client client) {
        log.info(String.format("Deleting client %s with id %s", client.getEmail(), client.getId()));
        return clientRepository.save(client);
    }

    public Client updateClient(Client client){
        log.info(String.format("Updating client %s with id %s", client.getFirstName(), client.getId()));
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findOne(id);
        if (client != null) {
            log.info(String.format("Deleting client %s with id %s", client.getEmail(), client.getId()));
            clientRepository.delete(client);
        }
    }

    public boolean exists(Long id){
        return clientRepository.exists(id);
    }
}
