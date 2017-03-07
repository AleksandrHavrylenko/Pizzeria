package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}
