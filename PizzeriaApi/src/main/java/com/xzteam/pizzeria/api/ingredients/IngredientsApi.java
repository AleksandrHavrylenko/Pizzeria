package com.xzteam.pizzeria.api.ingredients;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IngredientsApi {

    public String id;

    @NotNull
    @Size(min = 1, max = 50)
    public String name;

    @NotNull
    @Min(0)
    public Float price;

    @NotNull
    @Min(0)
    public Integer weight;

}
