package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.api.pizza.PizzaApiAddRequest;
import com.xzteam.pizzeria.api.pizza.PizzaApiListReply;
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

    @RequestMapping(path = "/pizzas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getAllPizzas() {
        PizzaApiListReply listReply = new PizzaApiListReply();
        listReply.pizzas.addAll(pizzaService.getAll()
                .stream()
                .map(d -> pizzaMapper.toApi(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/pizzas/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getPizzaById(@PathVariable Long id) {
        PizzaApiListReply reply = new PizzaApiListReply();
        reply.pizzas.add(pizzaMapper.toApi(pizzaService.getPizzaById(id)));
        return reply;
    }

    @RequestMapping(path = "/pizzas/byClientId/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getPizzasByClientId(@PathVariable Long id) {
        PizzaApiListReply listReply = new PizzaApiListReply();
        listReply.pizzas.addAll(pizzaService.getPizzasByClientId(id)
                .stream()
                .map(d -> pizzaMapper.toApi(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/pizzas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply addPizza(@RequestBody PizzaApiAddRequest req) {
        PizzaApiListReply reply = new PizzaApiListReply();
        try {
            Pizza pizza = pizzaService.addPizza(pizzaMapper.fromApi(req));
            reply.pizzas.add(pizzaMapper.toApi(pizza));
        } catch (Exception e) {
            String msg = "Error adding pizza: " + e.getMessage();
            reply.code = -1;
            reply.message = msg;
            log.warning(msg);
        }
        return reply;
    }

    @RequestMapping(path = "/pizzas/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
