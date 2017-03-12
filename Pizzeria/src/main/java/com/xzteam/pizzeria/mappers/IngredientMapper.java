package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.IngredientsApi;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.repository.IngredientRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Допиши методы:
 * newIngredient();
 * fromApi();
 *
 * @see DishMapper
 */
@Component
public class IngredientMapper {
	@Autowired
	IngredientRepository ingredientRepository;

    public IngredientsApi toApi(Ingredient ingredient) {
        IngredientsApi api = null;
        if (ingredient != null) {
            api = new IngredientsApi();
            api.ingredientId = ingredient.getId();
            api.name = ingredient.getName();
            api.price = ingredient.getPrice();
            api.weight = ingredient.getWeight();
            api.imageUrl = ingredient.getImageUrl();
        }
        return api;
    }
    
    private Ingredient newIngredients() {
        Ingredient au = new Ingredient();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !ingredientRepository.exists(id);
        }
        au.setId(id);
        return au;
    }

    public Ingredient fromApi(IngredientsApi api) {
        Ingredient ingredient = null;
        if (api.ingredientId != null) {
            ingredient = ingredientRepository.findOne(api.ingredientId);
        }
        if (ingredient == null) {
            ingredient = newIngredients();
        }
        ingredient.setImageUrl(api.imageUrl);
        ingredient.setName(api.name);
        ingredient.setPrice(api.price);
        ingredient.setWeight(api.weight);
        return ingredient;
    }
}
