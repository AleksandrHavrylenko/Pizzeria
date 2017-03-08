package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BucketApi {
	@XmlElement(required = false)
	private Long id;
}
