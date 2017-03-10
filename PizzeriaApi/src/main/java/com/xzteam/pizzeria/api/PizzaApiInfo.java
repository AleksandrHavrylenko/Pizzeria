package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class PizzaApiInfo extends PizzaApi {
    @XmlElement(required = true)
    public List<IngredientsApi> ingredients = new ArrayList<>();
}
