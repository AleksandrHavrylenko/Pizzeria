package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.DishApi;
import com.xzteam.pizzeria.domain.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public DishApi toApi(Dish d) {
        DishApi da = null;
        if (d != null) {
            da = new DishApi();
            da.id = d.getId();
            da.name = d.getName();
            da.description = d.getDescription();
            da.price = d.getPrice();
            da.weight = d.getWeight();
            da.type = d.getType().toString();
            da.imageUrl = d.getImageUrl();
        }
        return da;
    }
}
