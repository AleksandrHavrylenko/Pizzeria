package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DishApi {
	@XmlElement(required=false)
    public Long id;
	@XmlElement(required=true)
    public String name;
	@XmlElement(required=true)
    public String description;
	@XmlElement(required=true)
    public String type;
	@XmlElement(required=true)
    public Float price;
	@XmlElement(required=true)
    public Integer weight;
	@XmlElement(required=true)
    public String imageUrl;
}
