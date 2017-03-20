package com.xzteam.pizzeria.api.ingredients;

import com.xzteam.pizzeria.api.GenericReply;

import java.util.ArrayList;
import java.util.List;

public class IngredientsApiListReply extends GenericReply {
    public List<IngredientsApi> ingredients = new ArrayList<>();
} 
