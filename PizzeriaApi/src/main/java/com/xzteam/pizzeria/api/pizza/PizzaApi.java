package com.xzteam.pizzeria.api.pizza;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class PizzaApi {

    public String id;

    @NotNull
    @Size(min = 1, max = 50)
    public String name;
}
