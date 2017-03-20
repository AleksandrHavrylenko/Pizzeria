package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class DishService {
    private static final Logger log = Logger.getLogger(DishService.class.getName());

    @Autowired
    DishRepository dishRepository;

    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    public Dish getDishById(Long id) {
        return dishRepository.findOne(id);
    }

    public Dish addDish(Dish dish) {
        log.info(String.format("Adding dish %s with id %s", dish.getName(), dish.getId()));
        return dishRepository.save(dish);
    }

    public Dish updateDish(Dish dish) {
        log.info(String.format("Updating dish %s with id %s", dish.getName(), dish.getId()));
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.findOne(id);
        if (dish != null) {
            log.info(String.format("Deleting dish %s with id %s", dish.getName(), dish.getId()));
            dishRepository.delete(dish);
        }
    }

    public boolean exists(Long id) {
        return dishRepository.exists(id);
    }

}
