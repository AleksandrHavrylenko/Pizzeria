package com.xzteam.pizzeria.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClientApiReply {
	@XmlElement
	public List<ClientApi> clientApi = new ArrayList<>();

}
