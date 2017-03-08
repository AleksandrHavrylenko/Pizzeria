package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PizzaApi {
	@XmlElement(required=false)
	public Long pizzaId;
	@XmlElement(required=true)
	public String name;	
}
