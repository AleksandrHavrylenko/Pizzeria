package com.xzteam.pizzeria.api.bucket;

import com.xzteam.pizzeria.api.dish.DishApi;
import com.xzteam.pizzeria.api.pizza.PizzaApi;

import java.util.ArrayList;
import java.util.List;

public class BucketApiInfo extends BucketApi {
    public final List<DishApi> dishes = new ArrayList<>();
    public final List<PizzaApi> pizzas = new ArrayList<>();
}
