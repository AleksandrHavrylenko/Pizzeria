package com.xzteam.pizzeria.api.pizza;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class PizzaApiAddRequest extends PizzaApi {

    @NotNull
    public final List<Long> ingredientsIds = new ArrayList<>();
    @NotNull
    public Long clientId;

}
