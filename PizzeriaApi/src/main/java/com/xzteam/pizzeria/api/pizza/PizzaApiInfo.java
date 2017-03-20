package com.xzteam.pizzeria.api.pizza;

import com.xzteam.pizzeria.api.ingredients.IngredientsApi;

import java.util.ArrayList;
import java.util.List;


public class PizzaApiInfo extends PizzaApi {
    public float price = 0.00f;
    public int weight = 0;
    public List<IngredientsApi> ingredients = new ArrayList<>();
}
