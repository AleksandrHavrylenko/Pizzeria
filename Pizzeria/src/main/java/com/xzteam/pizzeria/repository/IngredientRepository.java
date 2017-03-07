package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
