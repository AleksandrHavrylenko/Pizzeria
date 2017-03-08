package com.xzteam.pizzeria.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.repository.DishRepository;

@Service
public class DishService {
	@Autowired
	DishRepository dishRepository;
	
	public List<Dish> getAll(){
		return dishRepository.findAll();		
	}
	
	public Dish getDishById(Long id){
		Dish dish = dishRepository.findOne(id);
		return dish;
	}
}
