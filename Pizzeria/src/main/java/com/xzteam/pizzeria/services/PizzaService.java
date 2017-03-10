package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PizzaService {
    private static final Logger log = Logger.getLogger(PizzaService.class.getName());
    @Autowired
    PizzaRepository pizzaRepository;

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

    public void deletePizza(Long id) {
        Pizza pizza = pizzaRepository.findOne(id);
        if (pizza != null) {
            log.info("Deleting pizza with id %s" + pizza.getId());
            pizzaRepository.delete(pizza);
        }
    }
}
