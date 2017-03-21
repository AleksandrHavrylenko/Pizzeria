package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.ingredients.IngredientsApi;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.IngredientService;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
    @Autowired
    private IngredientService ingredientService;

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
            idOK = !ingredientService.exists(id);
        }
        ingredient.setId(id);
        return ingredient;
    }

    private void updateFields(Ingredient ingredient, IngredientsApi api){
        ingredient.setImageUrl(api.imageUrl);
        ingredient.setName(api.name);
        ingredient.setPrice(api.price);
        ingredient.setWeight(api.weight);
    }

    public Ingredient fromApiPost(IngredientsApi api){
        Ingredient ingredient = newIngredient();
        updateFields(ingredient, api);
        return ingredient;
    }

    public Ingredient fromApiPut(IngredientsApi api, long id) throws NotFoundException{
        Ingredient ingredient = ingredientService.getIngredientById(id);
        if (ingredient == null){
            throw new NotFoundException();
        }
        updateFields(ingredient, api);
        return ingredient;
    }
}
