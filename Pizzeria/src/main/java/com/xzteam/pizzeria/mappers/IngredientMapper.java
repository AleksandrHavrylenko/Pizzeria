package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.ingredients.IngredientsApi;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.repository.IngredientRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
    @Autowired
    IngredientRepository ingredientRepository;

    public IngredientsApi toApi(Ingredient ingredient) {
        IngredientsApi api = null;
        if (ingredient != null) {
            api = new IngredientsApi();
            api.id = ingredient.getId().toString();
            api.name = ingredient.getName();
            api.price = ingredient.getPrice();
            api.weight = ingredient.getWeight();
            api.imageUrl = ingredient.getImageUrl();
        }
        return api;
    }

    private Ingredient newIngredient() {
        Ingredient ingredient = new Ingredient();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !ingredientRepository.exists(id);
        }
        ingredient.setId(id);
        return ingredient;
    }

    public Ingredient fromApi(IngredientsApi api) {
        Ingredient ingredient = null;
        if (api.id != null) {
            ingredient = ingredientRepository.findOne(Long.parseLong(api.id));
        }
        if (ingredient == null) {
            ingredient = newIngredient();
        }
        ingredient.setImageUrl(api.imageUrl);
        ingredient.setName(api.name);
        ingredient.setPrice(api.price);
        ingredient.setWeight(api.weight);
        return ingredient;
    }

}
