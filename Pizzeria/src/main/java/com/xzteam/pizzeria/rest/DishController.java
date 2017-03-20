package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.dish.DishApi;
import com.xzteam.pizzeria.api.dish.DishApiListReply;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.mappers.DishMapper;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class DishController {
    private static final Logger log = Logger.getLogger(DishController.class.getName());
    @Autowired
    DishService dishService;
    @Autowired
    DishMapper dishMapper;

    @RequestMapping(path = "/dishes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply getAllDishes() {
        DishApiListReply dishApiReply = new DishApiListReply();
        dishApiReply.dishes.addAll(dishService.getAll()
                .stream()
                .map(d -> dishMapper.toApiGet(d))
                .collect(Collectors.toList()));
        return dishApiReply;
    }

    @RequestMapping(path = "/dishes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply getDishById(@PathVariable Long id) {
        DishApiListReply reply = new DishApiListReply();
        DishApi api = dishMapper.toApiGet(dishService.getDishById(id));
        reply.dishes.add(api);
        if (api == null) {
            throw new NotFoundException("Dish with id=" + id + " not found!");
        }
        return reply;
    }

    @RequestMapping(path = "/dishes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply addDish(@Valid @RequestBody DishApi req) {
        DishApiListReply reply = new DishApiListReply();
        try {
            Dish dish = dishService.addDish(dishMapper.fromApiPost(req));
            reply.dishes.add(dishMapper.toApiGet(dish));
        } catch (Exception e) {
            log.warning("Error adding dish: " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/dishes/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply updateDish(@PathVariable Long id, @Valid @RequestBody DishApi req) {
        if (!dishService.exists(id)) {
            String msg = "Dish with id=" + id + " not found!";
            log.warning("Error updating dish: " + msg);
            throw new NotFoundException(msg);
        }
        DishApiListReply reply = new DishApiListReply();
        try {
            Dish dish = dishService.updateDish(dishMapper.fromApiPut(req, id));
            reply.dishes.add(dishMapper.toApiGet(dish));
        } catch (Exception e) {
            log.warning("Error updating dish : " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/dishes/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteDish(@PathVariable Long id) {
        if (!dishService.exists(id)) {
            String msg = "Dish with id=" + id + " not found!";
            log.warning("Error deleting dish: " + msg);
            throw new NotFoundException(msg);
        }
        try {
            dishService.deleteDish(id);
        } catch (Exception e) {
            log.warning("Error deleting dish : " + e.getMessage());
            throw e;
        }
    }

}
