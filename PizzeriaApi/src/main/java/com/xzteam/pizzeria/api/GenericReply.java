package com.xzteam.pizzeria.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GenericReply {
    @XmlElement(required=true)
    public Integer code = 0;
    @XmlElement(required=true)
    public String apiVer = "0.0.1";
    @XmlElement
    public String message;
}
