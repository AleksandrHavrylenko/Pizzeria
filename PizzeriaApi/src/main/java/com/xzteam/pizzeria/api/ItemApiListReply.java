package com.xzteam.pizzeria.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ItemApiListReply extends GenericReply{
	@XmlElement
	public List<ItemApi> itemApi = new ArrayList<>();
}
