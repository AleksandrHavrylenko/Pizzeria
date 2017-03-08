package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	List<Item> findAll();
}
