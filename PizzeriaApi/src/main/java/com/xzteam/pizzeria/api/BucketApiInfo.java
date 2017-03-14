package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class BucketApiInfo extends BucketApi {
	@XmlElement(required = true)
	public List<DishApi> dishes = new ArrayList<>();
	@XmlElement(required = true)
	public List<PizzaApi> pizzas = new ArrayList<>();
}
