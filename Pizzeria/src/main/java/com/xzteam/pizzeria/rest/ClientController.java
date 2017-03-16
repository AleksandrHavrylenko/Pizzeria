package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.ClientApi;
import com.xzteam.pizzeria.api.ClientApiReply;
import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.mappers.ClientMapper;
import com.xzteam.pizzeria.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class ClientController {
	private static final Logger log = Logger.getLogger(ClientController.class.getName());

	@Autowired
	ClientService clientService;
	@Autowired
	ClientMapper clientMapper;

	@RequestMapping(path = "/clients/all", method = RequestMethod.GET,
			produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ClientApiReply getAllClients() {
		ClientApiReply clientApiReply = new ClientApiReply();
        clientApiReply.clients.addAll(clientService.getAll()
                .stream()
				.map(client -> clientMapper.toApi(client))
				.collect(Collectors.toList()));
		return clientApiReply;
	}

	@RequestMapping(path = "/clients/byid/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ClientApiReply getClientById(@PathVariable Long id) {
		ClientApiReply reply = new ClientApiReply();
        reply.clients.add(clientMapper.toApi(clientService.getClientById(id)));
        return reply;
	}

	@RequestMapping(path = "/clients/del/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GenericReply delClient(@PathVariable Long id) {
		GenericReply reply = new GenericReply();
		try{
			clientService.deleteClient(id);
		}catch(Exception e){
			reply.code = -1;
			reply.message = e.getMessage();
			log.warning("Error deleting client: " + e.getMessage());
		}
		return reply;
	}

	@RequestMapping(path = "/clients/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GenericReply addClient(@RequestBody ClientApi req) {
        GenericReply rep = new GenericReply();
        try {
            Client client = clientService.addClient(clientMapper.fromApi(req));
            rep.message = client.getId().toString();
        } catch (Exception e) {
			String msg = "Error adding client: " + e.getMessage();
			rep.code = -1;
            rep.message = msg;
			log.warning(msg);
		}
        return rep;
    }
}

