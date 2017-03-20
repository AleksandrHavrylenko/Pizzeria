package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.client.ClientApi;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.repository.ClientRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
	@Autowired
	ClientRepository clientRepository;
	
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
            idOK = !clientRepository.exists(id);
        }
        client.setId(id);
        return client;
    }

    public Client fromApi(ClientApi api) {
        Client client = null;
        if (api.id != null) {
            client = clientRepository.findOne(Long.parseLong(api.id));
        }
        if (client == null) {
        	client = newClient();
        }
        client.setEmail(api.email);
        client.setFirstName(api.firstName);
        client.setLastName(api.lastName);
        client.setPassHash(api.passHash);
        client.setPhone(api.phone);
        client.setSpentMoney(api.spentMoney);
        return client;
    }
}
