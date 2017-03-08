package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ingredients {
	@XmlElement(required=false)
	public Long ingredientId;
	@XmlElement(required=true)
	public String name;	
	@XmlElement(required=true)
	public Float price;
	@XmlElement(required=true)
	public String imageUrl;
	@XmlElement(required=true)
	public Integer weight;
}
