package com.xzteam.pizzeria.api.pizza;

import com.xzteam.pizzeria.api.GenericReply;

import java.util.ArrayList;
import java.util.List;


public class PizzaApiListReply extends GenericReply {
    public List<PizzaApiInfo> pizzas = new ArrayList<>();
}
