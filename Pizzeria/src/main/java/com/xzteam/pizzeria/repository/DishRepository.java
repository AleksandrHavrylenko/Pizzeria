package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {
    List<Dish> findAll();
}
