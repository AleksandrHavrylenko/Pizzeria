package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.pizza.PizzaApiAddRequest;
import com.xzteam.pizzeria.api.pizza.PizzaApiInfo;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.rest.exceptions.BadRequestException;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.ClientService;
import com.xzteam.pizzeria.services.IngredientService;
import com.xzteam.pizzeria.services.PizzaService;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PizzaMapper {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private IngredientMapper ingredientMapper;
    @Value("${myserver.default-id}")
    private long defaultIngredientId;

    public PizzaApiInfo toApiGet(Pizza p) {
        PizzaApiInfo api = null;
        if (p != null) {
            api = new PizzaApiInfo();
            api.id = p.getId().toString();
            api.name = p.getName();
            api.ingredients.addAll(ingredientService
                    .getAllIngredientsByPizza(p)
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
            idOK = !pizzaService.exists(id);
        }
        pizza.setId(id);
        return pizza;
    }

    private void updateFields(Pizza pizza, PizzaApiAddRequest api) {
        if (!api.ingredientsIds.contains(defaultIngredientId)) {
            throw new BadRequestException("Pizza must be have a one default ingredient!");
        }
        pizza.setName(api.name);
        Client client = api.clientId == null ? null : clientService.getClientById(api.clientId);
        if (client == null) {
            throw new BadRequestException("Pizza must be have a client creator id!");
        }
        pizza.setClient(client);

        List<Ingredient> ingredients = api.ingredientsIds.stream()
                .map(id -> ingredientService.getIngredientById(id))
                .collect(Collectors.toList());
        pizza.setIngredients(ingredients);
    }

    public Pizza fromApiPost(PizzaApiAddRequest api) {
        Pizza pizza = newPizza();
        updateFields(pizza, api);
        return pizza;
    }

    public Pizza fromApiPut(PizzaApiAddRequest api, long id) throws NotFoundException {
        Pizza dish = pizzaService.getPizzaById(id);
        if (dish == null) {
            throw new NotFoundException();
        }
        updateFields(dish, api);
        return dish;
    }

}
