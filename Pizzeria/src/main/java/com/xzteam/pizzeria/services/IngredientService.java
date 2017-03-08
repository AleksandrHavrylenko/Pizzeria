package com.xzteam.pizzeria.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.repository.IngredientRepository;

@Service
public class IngredientService {
	@Autowired 
	IngredientRepository ingredientRepository;
	
	public List<Ingredient> getAll(){
		return ingredientRepository.findAll();
	}
	
	public Ingredient getIngredientById(Long id){
		Ingredient ingredient = ingredientRepository.findOne(id);
		return ingredient;
	}	
}
