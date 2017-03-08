package com.xzteam.pizzeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.repository.PizzaRepository;

@Service
public class PizzaService {
	@Autowired
	PizzaRepository pizzaRepository;
		
	public List<Pizza> findAll(){
		return pizzaRepository.findAll();
	}
	
	public Pizza findPizzaById(Long id){
		Pizza pizza = pizzaRepository.findOne(id);
		return pizza;
	}
}
