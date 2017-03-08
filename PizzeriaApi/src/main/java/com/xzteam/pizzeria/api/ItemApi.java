package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemApi {
	@XmlElement(required = false)
	public Long id;
	@XmlElement(required = true)
	public Float price;
}
