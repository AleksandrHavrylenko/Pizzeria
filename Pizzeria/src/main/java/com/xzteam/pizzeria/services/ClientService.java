package com.xzteam.pizzeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> getAll(){
		return clientRepository.findAll();
	}
	
	public Client getClientById(Long id){
		Client client = clientRepository.findOne(id);
		return client;
	}	
}
