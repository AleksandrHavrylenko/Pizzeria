package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findOne(id);
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.findOne(id);
        if (dish != null) {
            dishRepository.delete(dish);
        }
    }
}
