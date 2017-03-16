package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class BucketApiAddRequest extends BucketApi {
    @XmlElement(required = true)
    public Long clientId;
    @XmlElement
    public List<Long> dishesIds = new ArrayList<>();
    @XmlElement
    public List<Long> pizzasIds = new ArrayList<>();
}
