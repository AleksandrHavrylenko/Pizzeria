package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class PizzaApi {
	@XmlElement
	public String id;
	@XmlElement(required=true)
	public String name;	
}
