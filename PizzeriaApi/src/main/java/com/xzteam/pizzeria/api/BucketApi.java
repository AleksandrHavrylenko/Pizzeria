package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

@XmlRootElement
public abstract class BucketApi {
	@XmlElement
	public String id;
	@XmlElement(required = true)
	public String address;
	@XmlElement(required = true)
	public Float price;
	@XmlElement(required = true)
	public Calendar date;
	@XmlElement(required = true)
	public Boolean status;
}
