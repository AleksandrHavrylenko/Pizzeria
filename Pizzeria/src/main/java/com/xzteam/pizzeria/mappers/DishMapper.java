package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.dish.DishApi;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.DishService;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    @Autowired
    private DishService dishService;

    public DishApi toApiGet(Dish d) {
        DishApi api = null;
        if (d != null) {
            api = new DishApi();
            api.id = d.getId().toString();
            api.name = d.getName();
            api.description = d.getDescription();
            api.price = d.getPrice();
            api.weight = d.getWeight();
            api.type = d.getType().toString();
        }
        return api;
    }

    private Dish newDish() {
        Dish dish = new Dish();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !dishService.exists(id);
        }
        dish.setId(id);
        return dish;
    }

    private void updateFields(Dish dish, DishApi api) {
        dish.setName(api.name);
        dish.setDescription(api.description);
        dish.setPrice(api.price);
        dish.setWeight(api.weight);
        dish.setType(DishType.valueOf(api.type));
    }

    public Dish fromApiPost(DishApi api) {
        Dish dish = newDish();
        updateFields(dish, api);
        return dish;
    }

    public Dish fromApiPut(DishApi api, long id) throws NotFoundException {
        Dish dish = dishService.getDishById(id);
        if (dish == null) {
            throw new NotFoundException();
        }
        updateFields(dish, api);
        return dish;
    }
}
