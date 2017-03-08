package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PizzaApi {
	@XmlElement(required=false)
    private Long pizzaId;
	@XmlElement(required=true)
    private String name;	
}
