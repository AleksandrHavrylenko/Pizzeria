package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.pizza.PizzaApiAddRequest;
import com.xzteam.pizzeria.api.pizza.PizzaApiInfo;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.repository.ClientRepository;
import com.xzteam.pizzeria.repository.IngredientRepository;
import com.xzteam.pizzeria.repository.PizzaRepository;
import com.xzteam.pizzeria.rest.exceptions.BadRequestException;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PizzaMapper {

    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    IngredientMapper ingredientMapper;
    @Autowired
    ClientRepository clientRepository;


    public PizzaApiInfo toApi(Pizza p) {
        PizzaApiInfo api = null;
        if (p != null) {
            api = new PizzaApiInfo();
            api.id = p.getId().toString();
            api.name = p.getName();
            api.ingredients.addAll(ingredientRepository
                    .findAllIngredientsByPizzas(p)
                    .stream()
                    .map(i -> ingredientMapper.toApi(i))
                    .collect(Collectors.toList()));
            api.price = (float) api.ingredients.stream().mapToDouble(value -> value.price).sum();
            api.weight = api.ingredients.stream().mapToInt(value -> value.weight).sum();
        }
        return api;
    }

    private Pizza newPizza() {
        Pizza pizza = new Pizza();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !pizzaRepository.exists(id);
        }
        pizza.setId(id);
        return pizza;
    }

    public Pizza fromApi(PizzaApiAddRequest api) {
        Pizza pizza = null;
        if (api.id != null) {
            pizza = pizzaRepository.findOne(Long.parseLong(api.id));
        }
        if (pizza == null) {
            pizza = newPizza();
        }

        //TODO: Create property
        Long defaultIngredientId = 1000L;

        if (!api.ingredientsIds.contains(defaultIngredientId)) {
            throw new BadRequestException("Pizza must be have a one default ingredient!");
        }

        pizza.setName(api.name);
        Client client = api.clientId == null ? null : clientRepository.findOne(api.clientId);
        if (client == null) {
            throw new BadRequestException("Pizza must be have a client creator id!");
        }
        pizza.setClient(client);

        List<Ingredient> ingredients = api.ingredientsIds.stream()
                .map(id -> ingredientRepository.findOne(id))
                .collect(Collectors.toList());
        pizza.setIngredients(ingredients);
        return pizza;
    }
}
