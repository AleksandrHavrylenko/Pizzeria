package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.repository.ClientRepository;
import com.xzteam.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PizzaService {
    private static final Logger log = Logger.getLogger(PizzaService.class.getName());

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private ClientRepository clientRepository;

    public List<Pizza> getAll() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findOne(id);
    }

    public Pizza addPizza(Pizza pizza) {
        log.info("Adding pizza with id %s" + pizza.getId());
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Pizza pizza) {
        log.info("Updating pizza with id %s" + pizza.getId());
        return pizzaRepository.save(pizza);
    }

    public void deletePizza(Long id) {
        Pizza pizza = pizzaRepository.findOne(id);
        if (pizza != null) {
            log.info("Deleting pizza with id %s" + pizza.getId());
            pizzaRepository.delete(pizza);
        }
    }

    public List<Pizza> getPizzasByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        return client == null ? Collections.EMPTY_LIST : pizzaRepository.findAllPizzasByClient(client);
    }

    public List<Pizza> getAllPizzasInBucket(Bucket bucket) {
        return pizzaRepository.findAllPizzasByBuckets(bucket);
    }

    public boolean exists(Long id) {
        return clientRepository.exists(id);
    }
}
