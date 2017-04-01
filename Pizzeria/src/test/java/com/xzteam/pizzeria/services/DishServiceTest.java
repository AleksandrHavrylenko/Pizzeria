package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.Helper;
import com.xzteam.pizzeria.api.dish.DishApi;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * Created by qwerty on 31.03.2017.
 */
//@Sql({"before_test.sql", "after_test.sql"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    //@Order()
    //@Sql("file:C:/Users/qwerty/pizzeria/Pizzeria/sql-scripts/before_test.sql")
    @BeforeClass
    public static void before(){
        Helper.executeSql("C:/Users/qwerty/pizzeria/Pizzeria/sql-scripts/before_test.sql");
    }

    //@Sql("file:C:/Users/qwerty/pizzeria/Pizzeria/sql-scripts/after_test.sql")
    @AfterClass
    public static void after(){
        Helper.executeSql("C:/Users/qwerty/pizzeria/Pizzeria/sql-scripts/after_test.sql");
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
        //dishService.deleteDish(d.getId());
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
        Assert.assertTrue(b);
    }

}