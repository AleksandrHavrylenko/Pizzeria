package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class IngredientService {
    private static final Logger log = Logger.getLogger(IngredientService.class.getName());
    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findOne(id);
    }

    public Ingredient addIngredient(Ingredient ingredient) {
        log.info(String.format("Adding ingredient %s with id %s", ingredient.getName(), ingredient.getId()));
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Ingredient ingredient){
        log.info(String.format("Updating ingredient %s with id %s", ingredient.getName(), ingredient.getId()));
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        Ingredient ingredient = ingredientRepository.findOne(id);
        if (ingredient != null) {
            log.info(String.format("Deleting ingredient %s with id %s",
                    ingredient.getName(), ingredient.getId()));
            ingredientRepository.delete(ingredient);
        }
    }

    public boolean exists(Long id){
        return ingredientRepository.exists(id);
    }
}
