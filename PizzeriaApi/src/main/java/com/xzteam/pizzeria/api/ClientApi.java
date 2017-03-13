package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientApi {
    @XmlElement
    public String id;
    @XmlElement(required = true)
	public String email;
	@XmlElement(required = true)
	public String passHash;
	@XmlElement(required = true)
	public String firstName;
	@XmlElement(required = true)
	public String lastName;
	@XmlElement(required = true)
	public String phone;
	@XmlElement(required = true)
	public Float spentMoney;

}
