package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.api.dish.DishApi;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by qwerty on 31.03.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    @Sql("before_test.sql")
    @Sql(scripts = "before_test.sql", executionPhase = BEFORE_TEST_METHOD)
    public void before(){

    }

    @Test
    @Sql("after_test.sql")
    @Sql(scripts = "after_test.sql", executionPhase = AFTER_TEST_METHOD)
    public void after(){

    }

    @Test
    public void getAll() throws Exception {
        List<Dish> dishes = dishService.getAll();
        Assert.assertNotNull(dishes);
    }

    @Test
    public void getDishById() throws Exception {
        Dish dish = dishService.getDishById(dishService.getAll().get(0).getId());
        Assert.assertNotNull(dish);
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

        Dish d = dishService.getAll().get(0);
        Assert.assertNotNull(d);
        assertEquals(d.getName(), dish.getName());
        assertEquals(d.getDescription(), dish.getDescription());
        assertEquals(d.getPrice(), dish.getPrice());
        assertEquals(d.getWeight(), dish.getWeight());
        assertEquals(d.getType(), dish.getType());
        dishService.deleteDish(d.getId());
    }

    @Test
    public void updateDish() throws Exception {
        Dish d = dishService.getAll().get(0);
        d.setName("TestDish");
        d.setDescription("TestDescription");
        d.setPrice(100.0f);
        d.setWeight(200);
        d.setType(DishType.SECOND_DISH);
        dishService.updateDish(d);

        Dish de = dishService.getAll().get(0);
        Assert.assertNotNull(de);
        assertEquals(d.getName(), de.getName());
        assertEquals(d.getDescription(), de.getDescription());
        assertEquals(d.getPrice(), de.getPrice());
        assertEquals(d.getWeight(), de.getWeight());
        assertEquals(d.getType(), de.getType());
    }

    @Test
    public void deleteDish() throws Exception {
        Dish dish = new Dish();
        dish.setDescription("description1");
        dish.setWeight(40);
        dish.setName("name1");
        dish.setPrice(560.0f);
        dish.setType(DishType.DESSERT);
        dishService.addDish(dish);

        Dish d = dishService.getAll().get(0);
        Assert.assertNotNull(d);
        assertEquals(d.getName(), dish.getName());
        assertEquals(d.getDescription(), dish.getDescription());
        assertEquals(d.getPrice(), dish.getPrice());
        assertEquals(d.getWeight(), dish.getWeight());
        assertEquals(d.getType(), dish.getType());
        Long id = d.getId();
        dishService.deleteDish(d.getId());
        dish = dishService.getDishById(id);
        Assert.assertNull(dish);
    }

    @Test
    public void exists() throws Exception {
        Dish dish = dishService.getAll().get(0);
        boolean b = dishService.exists(dish.getId());
        Assert.assertTrue(b);
    }

}