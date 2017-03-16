package com.xzteam.pizzeria;

import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.enums.DishType;
import com.xzteam.pizzeria.repository.DishRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PizzeriaApplicationTests {

    @Autowired
    DishRepository dishRepository;

    @Test
    @Ignore
    public void simpleTest() {
        Dish dish = dishRepository.findOne(1L);
        Assert.assertEquals(dish.getType(), DishType.PIZZA);
        Assert.assertEquals(dish.getPrice(), 45f, 0.1f);
    }

    @Test(expected = NullPointerException.class)
    @Ignore
    public void testGetNull() {
        Dish dish = dishRepository.findOne(-1L);
        dish.getName();
    }

}
