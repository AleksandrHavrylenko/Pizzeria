package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.api.PizzaApiAddRequest;
import com.xzteam.pizzeria.api.PizzaApiListReply;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.mappers.PizzaMapper;
import com.xzteam.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class PizzaController {

    private static final Logger log = Logger.getLogger(PizzaController.class.getName());

    @Autowired
    PizzaService pizzaService;
    @Autowired
    PizzaMapper pizzaMapper;

    @RequestMapping(path = "/pizzas/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getAllPizzas() {
        PizzaApiListReply listReply = new PizzaApiListReply();
        listReply.pizzas.addAll(pizzaService.getAll()
                .stream()
                .map(d -> pizzaMapper.toApi(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/pizzas/byid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getDishById(@PathVariable Long id) {
        PizzaApiListReply reply = new PizzaApiListReply();
        reply.pizzas.add(pizzaMapper.toApi(pizzaService.getPizzaById(id)));
        return reply;
    }

    @RequestMapping(path = "/pizzas/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply addPizza(@RequestBody PizzaApiAddRequest req) {
        GenericReply rep = new GenericReply();
        try {
            Pizza pizza = pizzaService.addPizza(pizzaMapper.fromApi(req));
            rep.message = pizza.getId().toString();
        } catch (Exception e) {
            String msg = "Error adding pizza: " + e.getMessage();
            rep.code = -1;
            rep.message = msg;
            log.warning(msg);
        }
        return rep;
    }

    @RequestMapping(path = "/pizzas/del/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delPizza(@PathVariable Long id) {
        GenericReply rep = new GenericReply();
        try {
            pizzaService.deletePizza(id);
        } catch (Exception e) {
            String msg = "Error deleting pizza: " + e.getMessage();
            rep.code = -1;
            rep.message = msg;
            log.warning(msg);
        }
        return rep;
    }

}
