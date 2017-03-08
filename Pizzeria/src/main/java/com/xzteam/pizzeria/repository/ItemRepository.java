package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Item;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	public List<Item> findAllItemByClient(Client client);
	public List<Item> findAll();
}
