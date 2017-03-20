package com.xzteam.pizzeria.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzteam.pizzeria.api.dish.DishApi;
import com.xzteam.pizzeria.api.dish.DishApiListReply;
import com.xzteam.pizzeria.domain.enums.DishType;
import org.junit.Assert;
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
    public void addDish() throws Exception {
        DishApi d = new DishApi();
        d.name = "TestDish";
        d.description = "TestDescription";
        d.price = 100.0f;
        d.weight = 200;
        d.type = DishType.SECOND_DISH.toString();
        d.imageUrl = "http://test.com";

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
        DishApiListReply gr = om.readValue(reply, DishApiListReply.class);
        //assertEquals("Return code is not 0", gr.code.longValue(), 0L);
        DishApi replyDish = gr.dishes.get(0);

        Assert.assertNotNull(replyDish);
        assertEquals(d.name, replyDish.name);
        assertEquals(d.description, replyDish.description);
        assertEquals(d.price, replyDish.price);
        assertEquals(d.weight, replyDish.weight);
        assertEquals(d.type, replyDish.type);

        //if (gr.code == 0) {
        long id = Long.parseLong(replyDish.id);
            mockMvc.perform(delete("/dishes/del/" + id)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
                    .andExpect(status().isOk());
        //}

    }

    @Test
    public void updateDish() throws Exception {
        DishApi d = new DishApi();
        d.name = "TestDish";
        d.description = "TestDescription";
        d.price = 100.0f;
        d.weight = 200;
        d.type = DishType.SECOND_DISH.toString();
        d.imageUrl = "http://test.com";

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
        DishApiListReply gr = om.readValue(reply, DishApiListReply.class);
        //assertEquals("Return code is not 0", gr.code.longValue(), 0L);
        DishApi replyDish = gr.dishes.get(0);

        Assert.assertNotNull(replyDish);
        assertEquals(d.name, replyDish.name);
        assertEquals(d.description, replyDish.description);
        assertEquals(d.price, replyDish.price);
        assertEquals(d.weight, replyDish.weight);
        assertEquals(d.type, replyDish.type);

        String newId = gr.dishes.get(0).id;

        //Update
        d.id = newId;
        d.name = "UpdatedDish";
        d.description = "UpdatedDescription";


        ObjectMapper om1 = new ObjectMapper();
        String content1 = om.writeValueAsString(d);

        MvcResult result1 = mockMvc.perform(put("/dishes/update")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content1)
        )
                .andExpect(status().isOk())
                .andReturn();

        String reply1 = result1.getResponse().getContentAsString();
        DishApiListReply gr1 = om1.readValue(reply1, DishApiListReply.class);
        //assertEquals("Return code is not 0", gr1.code.longValue(), 0L);
        replyDish = gr1.dishes.get(0);

        Assert.assertNotNull(replyDish);
        assertEquals(newId, replyDish.id);
        assertEquals(d.name, replyDish.name);
        assertEquals(d.description, replyDish.description);
        assertEquals(d.price, replyDish.price);
        assertEquals(d.weight, replyDish.weight);
        assertEquals(d.type, replyDish.type);


//        if (gr1.code == 0) {
//            long id = Long.parseLong(replyDish.id);
//            mockMvc.perform(delete("/dishes/del/" + id)
//                    .accept(MediaType.APPLICATION_JSON_UTF8)
//            )
//                    .andExpect(status().isOk());
//        }
    }

    @Test
    @Ignore
    public void getDishById() throws Exception {
        this.mockMvc.perform(get("/dishes/byid/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestDescription")));
    }

    @Test
    @Ignore
    public void getAllDishes() throws Exception {

    }


    @Test
    public void delDish() throws Exception {
        this.mockMvc.perform(delete("/dishes/del/1")).andDo(print()).andExpect(status().isOk());
    }

}