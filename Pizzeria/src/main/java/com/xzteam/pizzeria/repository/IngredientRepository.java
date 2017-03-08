package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.domain.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    List<Ingredient> findAll();
    List<Ingredient> findAllIngredientsByPizzas(Pizza pizza);
}
