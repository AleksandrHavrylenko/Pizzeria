package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by qwerty on 31.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    public void before(){

    }

    public void after(){

    }

    @Test
    public void getAll() throws Exception {
        List<Dish> dishes = dishService.getAll();
        //Assert.assertNull(dishes);
    }

    @Test
    public void getDishById() throws Exception {
        Dish dish = dishService.getDishById(dishService.getAll().get(0).getId());
    }

    @Test
    public void addDish() throws Exception {
        Dish dish = new Dish();
        dish.setDescription("description");
        dish.setWeight(40);
        dish.setName("name");
        dish.setPrice(560.0f);
        dish.setType(DishType.DESSERT);
        dishService.addDish(dish);
    }

    @Test
    public void updateDish() throws Exception {
        Dish dish = dishService.getAll().get(0);
        dishService.updateDish(dish);
    }

    @Test
    public void deleteDish() throws Exception {
        dishService.deleteDish(dishService.getAll().get(0).getId());
    }

    @Test
    public void exists() throws Exception {
        Dish dish = dishService.getAll().get(0);
        Assert.assertNotNull(dish);
    }

}