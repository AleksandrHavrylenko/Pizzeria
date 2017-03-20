package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.api.ingredients.IngredientsApi;
import com.xzteam.pizzeria.api.ingredients.IngredientsApiListReply;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.mappers.IngredientMapper;
import com.xzteam.pizzeria.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class IngredientController {

    private static final Logger log = Logger.getLogger(IngredientController.class.getName());

    @Autowired
    IngredientService ingredientService;
    @Autowired
    IngredientMapper ingredientMapper;

    @RequestMapping(path = "/ingredients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply getAllIngredients() {
        IngredientsApiListReply listReply = new IngredientsApiListReply();
        listReply.ingredients.addAll(ingredientService.getAll()
                .stream()
                .map(d -> ingredientMapper.toApi(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/ingredients/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply getIngredientById(@PathVariable Long id) {
        IngredientsApiListReply reply = new IngredientsApiListReply();
        reply.ingredients.add(ingredientMapper.toApi(ingredientService.getIngredientById(id)));
        return reply;
    }

    @RequestMapping(path = "/ingredients", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply addIngredient(@RequestBody IngredientsApi req) {
        IngredientsApiListReply reply = new IngredientsApiListReply();
        try {
            Ingredient ing = ingredientService.addIngredient(ingredientMapper.fromApi(req));
            reply.ingredients.add(ingredientMapper.toApi(ing));
        } catch (Exception e) {
            reply.code = -1;
            reply.message = e.getMessage();
        }
        return reply;
    }

    @RequestMapping(path = "/ingredients/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delIngredient(@PathVariable Long id) {
        GenericReply rep = new GenericReply();
        try {
            ingredientService.deleteIngredient(id);
        } catch (Exception e) {
            String msg = "Error deleting ingredient: " + e.getMessage();
            rep.code = -1;
            rep.message = msg;
            log.warning(msg);
        }
        return rep;
    }
}
