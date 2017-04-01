package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.Helper;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    @BeforeClass
    public static void before(){
        Helper.executeSql("before_test.sql");
    }

    @AfterClass
    public static void after(){
        Helper.executeSql("after_test.sql");
    }

    @Test
    public void getAll() throws Exception {
        List<Dish> dishes = dishService.getAll();
        Assert.assertNotNull(dishes);
        Assert.assertTrue(dishes.size() >= 5);
    }

    @Test
    public void getDishById() throws Exception {
        Dish dish = dishService.getDishById(-1L);
        Assert.assertNotNull(dish);
        Assert.assertEquals(dish.getType(), DishType.DRINK);
        Assert.assertEquals(dish.getName(), "Тестовое блюдо 1");
        Assert.assertEquals(Long.valueOf(dish.getWeight()), Long.valueOf(500));
        Assert.assertEquals(Float.valueOf(dish.getPrice()), Float.valueOf(21));
        Assert.assertEquals(dish.getDescription(), "Описание 1");
    }

    @Test
    public void addDish() throws Exception {
        Dish dish = new Dish();
        dish.setDescription("description");
        dish.setWeight(40);
        dish.setName("name");
        dish.setPrice(560.0f);
        dish.setType(DishType.DESSERT);
        dish.setId(-6L);
        dishService.addDish(dish);

        Dish d = dishService.getDishById(-6L);
        Assert.assertNotNull(d);
        Assert.assertEquals(d.getName(), dish.getName());
        Assert.assertEquals(d.getDescription(), dish.getDescription());
        Assert.assertEquals(d.getPrice(), dish.getPrice());
        Assert.assertEquals(d.getWeight(), dish.getWeight());
        Assert.assertEquals(d.getType(), dish.getType());
    }

    @Test
    public void updateDish() throws Exception {
        Dish d = dishService.getDishById(-6L);
        Dish de = new Dish();
        de.setName(d.getName());
        de.setDescription(d.getDescription());
        de.setPrice(d.getPrice());
        de.setWeight(d.getWeight());
        de.setType(d.getType());
        d.setName("TestDish");
        d.setDescription("TestDescription");
        d.setPrice(100.0f);
        d.setWeight(200);
        d.setType(DishType.SECOND_DISH);
        dishService.updateDish(d);

        d = dishService.getDishById(-6L);
        Assert.assertNotNull(d);
        Assert.assertNotEquals(d.getName(), de.getName());
        Assert.assertNotEquals(d.getDescription(), de.getDescription());
        Assert.assertNotEquals(d.getPrice(), de.getPrice());
        Assert.assertNotEquals(d.getWeight(), de.getWeight());
        Assert.assertNotEquals(d.getType(), de.getType());
    }

    @Test
    public void deleteDish() throws Exception {

        Dish d = dishService.getDishById(-6L);
        Assert.assertNotNull(d);
        Long id = d.getId();
        dishService.deleteDish(d.getId());
        Dish dish = dishService.getDishById(id);
        Assert.assertNull(dish);
    }

    @Test
    public void exists() throws Exception {
        Dish dish = dishService.getDishById(-1L);
        boolean b = dishService.exists(dish.getId());
        boolean n = dishService.exists(-50L);
        Assert.assertTrue(b);
        Assert.assertFalse(n);
    }

}