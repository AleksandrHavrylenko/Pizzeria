package com.xzteam.pizzeria.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzteam.pizzeria.api.DishApi;
import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.domain.enums.DishType;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    public void getAllDishes() throws Exception {

    }

    @Test
    public void getDishById() throws Exception {
        this.mockMvc.perform(get("/dishes/byid/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ukranian_soup")));
    }

    @Test
    public void addDish() throws Exception {
        DishApi d = new DishApi();
        d.name = "TestDish";
        d.description = "TestDescription";
        d.price = 100.0f;
        d.weight = 200;
        d.type = DishType.SECOND_DISH.toString();
        d.imageUrl = "http://test";

        ObjectMapper om = new ObjectMapper();
        String content = om.writeValueAsString(d);

        MvcResult result = mockMvc.perform(post("/dishes/add")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
        )
                .andExpect(status().isOk())
                .andReturn();

        String reply = result.getResponse().getContentAsString();
        GenericReply gr = om.readValue(reply, GenericReply.class);
        assertEquals("Return code is not 0", gr.code.longValue(), 0L);

        if (gr.code == 0) {
            long id = Long.parseLong(gr.message);
            mockMvc.perform(delete("/dishes/del/" + id)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk());
        }

    }

    @Test
    @Ignore
    public void delDish() throws Exception {

    }

}