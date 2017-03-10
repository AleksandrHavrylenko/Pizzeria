package com.xzteam.pizzeria.rest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IngredientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void getAllIngredients() throws Exception {

    }

    @Test
    public void getIngredientById() throws Exception {

    }

    @Test
    public void addIngredient() throws Exception {

    }

    @Test
    @Ignore
    public void delIngredient() throws Exception {

    }

}