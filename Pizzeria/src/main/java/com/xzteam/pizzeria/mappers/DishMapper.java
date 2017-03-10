package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.DishApi;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import com.xzteam.pizzeria.repository.DishRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * EXAMPLE MAPPER
 */
@Component
public class DishMapper {

    @Autowired
    DishRepository dishRepository;

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

    private Dish newDish() {
        Dish au = new Dish();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !dishRepository.exists(id);
        }
        au.setId(id);
        return au;
    }

    public Dish fromApi(DishApi api) {
        Dish dish = null;
        if (api.id != null) {
            dish = dishRepository.findOne(api.id);
        }
        if (dish == null) {
            dish = newDish();
        }
        dish.setName(api.name);
        dish.setDescription(api.description);
        dish.setPrice(api.price);
        dish.setWeight(api.weight);
        dish.setImageUrl(api.imageUrl);
        dish.setType(DishType.valueOf(api.type));
        return dish;
    }
}
