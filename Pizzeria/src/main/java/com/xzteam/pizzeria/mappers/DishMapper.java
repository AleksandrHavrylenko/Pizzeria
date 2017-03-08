package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.DishApi;
import com.xzteam.pizzeria.domain.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public DishApi toApi(Dish d) {
        DishApi api = null;
        if (d != null) {
            api = new DishApi();
            api.id = d.getId();
            api.name = d.getName();
            api.description = d.getDescription();
            api.price = d.getPrice();
            api.weight = d.getWeight();
            api.type = d.getType().toString();
            api.imageUrl = d.getImageUrl();
        }
        return api;
    }
}
