package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

}
