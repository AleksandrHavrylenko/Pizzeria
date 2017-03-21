package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.client.ClientApi;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.ClientService;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    @Autowired
    private ClientService clientService;

    public ClientApi toApi(Client client) {
    	ClientApi clientApi = null;
    	if(client != null) {
    		clientApi = new ClientApi();
            clientApi.id = client.getId().toString();
            clientApi.email = client.getEmail();
    		clientApi.passHash = client.getPassHash();
    		clientApi.firstName = client.getFirstName();
    		clientApi.lastName = client.getLastName();
    		clientApi.phone = client.getPhone();
    		clientApi.spentMoney = client.getSpentMoney();
    	}
    	return clientApi;
    }
    
    private Client newClient() {
        Client client = new Client();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !clientService.exists(id);
        }
        client.setId(id);
        return client;
    }

    private void updateFields(Client client, ClientApi api){
        client.setEmail(api.email);
        client.setFirstName(api.firstName);
        client.setLastName(api.lastName);
        client.setPassHash(api.passHash);
        client.setPhone(api.phone);
        client.setSpentMoney(api.spentMoney);
    }

    public Client fromApiPost(ClientApi api){
        Client client = newClient();
        updateFields(client, api);
        return client;
    }

    public Client fromApiPut(ClientApi api, long id) throws NotFoundException{
        Client client = clientService.getClientById(id);
        if(client == null){
            throw new NotFoundException();
        }
        updateFields(client, api);
        return client;
    }
}
