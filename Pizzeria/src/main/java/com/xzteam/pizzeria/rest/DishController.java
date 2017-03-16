package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.DishApi;
import com.xzteam.pizzeria.api.DishApiListReply;
import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.mappers.DishMapper;
import com.xzteam.pizzeria.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class DishController {

    private static final Logger log = Logger.getLogger(DishController.class.getName());

    @Autowired
    DishService dishService;
    @Autowired
    DishMapper dishMapper;

    @RequestMapping(path = "/dishes/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply getAllDishes() {
        DishApiListReply dishApiReply = new DishApiListReply();
        dishApiReply.dishes.addAll(dishService.getAll()
                .stream()
                .map(d -> dishMapper.toApi(d))
                .collect(Collectors.toList()));
        return dishApiReply;
    }

    @RequestMapping(path = "/dishes/byid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply getDishById(@PathVariable Long id) {
        DishApiListReply reply = new DishApiListReply();
        reply.dishes.add(dishMapper.toApi(dishService.getDishById(id)));
        return reply;
    }

    @RequestMapping(path = "/dishes/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply addDish(@RequestBody DishApi req) {
        DishApiListReply reply = new DishApiListReply();
        try {
            Dish dish = dishService.addDish(dishMapper.fromApi(req));
            reply.dishes.add(dishMapper.toApi(dish));
        } catch (Exception e) {
            String msg = "Error adding dish: " + e.getMessage();
            reply.code = -1;
            reply.message = msg;
            log.warning(msg);
        }
        return reply;
    }

    @RequestMapping(path = "/dishes/del/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delDish(@PathVariable Long id) {
        GenericReply rep = new GenericReply();
        try {
            dishService.deleteDish(id);
        } catch (Exception e) {
            String msg = "Error deleting dish: " + e.getMessage();
            rep.code = -1;
            rep.message = msg;
            log.warning(msg);
        }
        return rep;
    }

}
