package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.client.ClientApi;
import com.xzteam.pizzeria.api.client.ClientApiReply;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.mappers.ClientMapper;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class ClientController {
    private static final Logger log = Logger.getLogger(ClientController.class.getName());

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;

    @RequestMapping(path = "/clients", method = RequestMethod.GET,
            produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientApiReply getAllClients() {
        ClientApiReply clientApiReply = new ClientApiReply();
        clientApiReply.clients.addAll(clientService.getAll()
                .stream()
                .map(client -> clientMapper.toApi(client))
                .collect(Collectors.toList()));
        return clientApiReply;
    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientApiReply getClientById(@PathVariable Long id) {
        ClientApi api = clientMapper.toApi(clientService.getClientById(id));
        if (api == null) {
            throw new NotFoundException("Client with id=" + id + " not found!");
        }
        ClientApiReply reply = new ClientApiReply();
        reply.clients.add(api);
        return reply;
    }

    @RequestMapping(path = "/clients", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientApiReply addClient(@Valid @RequestBody ClientApi req) {
        ClientApiReply reply = new ClientApiReply();
        try {
            Client client = clientService.addClient(clientMapper.fromApiPost(req));
            reply.clients.add(clientMapper.toApi(client));
        } catch (Exception e) {
            log.warning("Error adding client: " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ClientApiReply updateClient(@PathVariable Long id, @Valid @RequestBody ClientApi req) {
        if (!clientService.exists(id)) {
            String msg = "Client with id=" + id + " not found!";
            log.warning("Error updating client: " + msg);
            throw new NotFoundException(msg);
        }
        ClientApiReply reply = new ClientApiReply();
        try {
            Client client = clientService.updateClient(clientMapper.fromApiPut(req, id));
            reply.clients.add(clientMapper.toApi(client));
        } catch (Exception e) {
            log.warning("Error updating client : " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/clients/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteClient(@PathVariable Long id) {
        if (!clientService.exists(id)) {
            String msg = "Client with id=" + id + " not found!";
            log.warning("Error deleting client: " + msg);
            throw new NotFoundException(msg);
        }
        try {
            clientService.deleteClient(id);
        } catch (Exception e) {
            log.warning("Error deleting client : " + e.getMessage());
            throw e;
        }
    }

}

