package com.xzteam.pizzeria.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xzteam.pizzeria.api.ClientApi;
import com.xzteam.pizzeria.api.DishApi;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import com.xzteam.pizzeria.repository.ClientRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;

@Component
public class ClientMapper {
	@Autowired
	ClientRepository clientRepository;
	
    public ClientApi toApi(Client client) {
    	ClientApi clientApi = null;
    	if(client != null) {
    		clientApi = new ClientApi();
    		clientApi.id = client.getId();
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
        Client au = new Client();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !clientRepository.exists(id);
        }
        au.setId(id);
        return au;
    }

    public Client fromApi(ClientApi api) {
        Client client = null;
        if (api.id != null) {
            client = clientRepository.findOne(api.id);
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
