package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.IngredientsApiListReply;
import com.xzteam.pizzeria.api.PizzaApiAddRequest;
import com.xzteam.pizzeria.api.PizzaApiInfo;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.repository.IngredientRepository;
import com.xzteam.pizzeria.repository.PizzaRepository;
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

    public PizzaApiInfo toApi(Pizza p) {
        PizzaApiInfo api = null;
        if (p != null) {
            api = new PizzaApiInfo();
            api.id = p.getId().toString();
            api.name = p.getName();
            IngredientsApiListReply listReply = new IngredientsApiListReply();
            listReply.ingredients.addAll(ingredientRepository
                    .findAllIngredientsByPizzas(p)
                    .stream()
                    .map(i -> ingredientMapper.toApi(i))
                    .collect(Collectors.toList()));
            api.ingredients = listReply.ingredients;
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
        pizza.setName(api.name);
        List<Ingredient> ingredients = api.ingredientsIds.stream()
                .map(id -> ingredientRepository.findOne(id))
                .collect(Collectors.toList());
        pizza.setIngredients(ingredients);
        return pizza;
    }
}
