package com.xzteam.pizzeria.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PizzaApiListReply extends GenericReply{
	@XmlElement(required=true)
    public List <PizzaApi> pizzaApi = new ArrayList<>();
}
