package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.pizza.PizzaApiAddRequest;
import com.xzteam.pizzeria.api.pizza.PizzaApiInfo;
import com.xzteam.pizzeria.api.pizza.PizzaApiListReply;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.mappers.PizzaMapper;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class PizzaController {
    private static final Logger log = Logger.getLogger(PizzaController.class.getName());

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private PizzaMapper pizzaMapper;

    @RequestMapping(path = "/pizzas", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getAllPizzas() {
        PizzaApiListReply listReply = new PizzaApiListReply();
        listReply.pizzas.addAll(pizzaService.getAll()
                .stream()
                .map(d -> pizzaMapper.toApiGet(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/pizzas/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getPizzaById(@PathVariable Long id) {
        PizzaApiInfo api = pizzaMapper.toApiGet(pizzaService.getPizzaById(id));
        if (api == null) {
            throw new NotFoundException("Pizza with id=" + id + " not found!");
        }
        PizzaApiListReply reply = new PizzaApiListReply();
        reply.pizzas.add(pizzaMapper.toApiGet(pizzaService.getPizzaById(id)));
        return reply;
    }

    @RequestMapping(path = "/pizzas/byClientId/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply getPizzasByClientId(@PathVariable Long id) {
        PizzaApiListReply listReply = new PizzaApiListReply();
        listReply.pizzas.addAll(pizzaService.getPizzasByClientId(id)
                .stream()
                .map(d -> pizzaMapper.toApiGet(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/pizzas", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply addPizza(@RequestBody PizzaApiAddRequest req) {
        PizzaApiListReply reply = new PizzaApiListReply();
        try {
            Pizza pizza = pizzaService.addPizza(pizzaMapper.fromApiPost(req));
            reply.pizzas.add(pizzaMapper.toApiGet(pizza));
        } catch (Exception e) {
            log.warning("Error adding pizza: " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/pizzas/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PizzaApiListReply updatePizza(@PathVariable Long id, @Valid @RequestBody PizzaApiAddRequest req) {
        if (!pizzaService.exists(id)) {
            String msg = "Pizza with id=" + id + " not found!";
            log.warning("Error updating pizza: " + msg);
            throw new NotFoundException(msg);
        }
        PizzaApiListReply reply = new PizzaApiListReply();
        try {
            Pizza dish = pizzaService.updatePizza(pizzaMapper.fromApiPut(req, id));
            reply.pizzas.add(pizzaMapper.toApiGet(dish));
        } catch (Exception e) {
            log.warning("Error updating pizza : " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/pizzas/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deletePizza(@PathVariable Long id) {
        if (!pizzaService.exists(id)) {
            String msg = "Pizza with id=" + id + " not found!";
            log.warning("Error deleting pizza: " + msg);
            throw new NotFoundException(msg);
        }
        try {
            pizzaService.deletePizza(id);
        } catch (Exception e) {
            log.warning("Error deleting pizza : " + e.getMessage());
            throw e;
        }
    }

}
