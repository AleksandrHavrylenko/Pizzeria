package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.IngredientsApi;
import com.xzteam.pizzeria.domain.Ingredient;
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
}
