package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
