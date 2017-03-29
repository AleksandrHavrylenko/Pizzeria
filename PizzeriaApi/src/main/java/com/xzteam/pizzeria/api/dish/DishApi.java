package com.xzteam.pizzeria.api.dish;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class DishApi {

    public String id;
    @NotNull
    @Size(min = 1, max = 50)
    public String name;

    @NotNull
    @Size(min = 1, max = 255)
    public String description;

    @NotNull
    @Pattern(regexp = "PIZZA|DRINK|LUNCH|FIRST_DISH|SECOND_DISH|DESSERT", message = "Unknown dish type")
    public String type;

    @NotNull
    @Min(0)
    public Float price;

    @NotNull
    @Min(0)
    public Integer weight;
}
