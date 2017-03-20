package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.ingredients.IngredientsApi;
import com.xzteam.pizzeria.api.ingredients.IngredientsApiListReply;
import com.xzteam.pizzeria.domain.Ingredient;
import com.xzteam.pizzeria.mappers.IngredientMapper;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        IngredientsApi api = ingredientMapper.toApi(ingredientService.getIngredientById(id));
        reply.ingredients.add(api);
        if (api == null) {
            throw new NotFoundException("Ingredient with id=" + id + " not found!");
        }
        return reply;
    }

    @RequestMapping(path = "/ingredients", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply addIngredient(@Valid @RequestBody IngredientsApi req) {
        IngredientsApiListReply reply = new IngredientsApiListReply();
        try {
            Ingredient ing = ingredientService.addIngredient(ingredientMapper.fromApiPost(req));
            reply.ingredients.add(ingredientMapper.toApi(ing));
        } catch (Exception e) {
            reply.code = -1;
            reply.message = e.getMessage();
            log.warning("Error adding ingredient: " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/ingredients/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientsApi req) {
        if (!ingredientService.exists(id)) {
            String msg = "ingredients with id=" + id + " not found!";
            log.warning("Error updating ingredient: " + msg);
            throw new NotFoundException(msg);
        }
        IngredientsApiListReply reply = new IngredientsApiListReply();
        try {
            Ingredient dish = ingredientService.updateIngredient(ingredientMapper.fromApiPut(req, id));
            reply.ingredients.add(ingredientMapper.toApi(dish));
        } catch (Exception e) {
            log.warning("Error updating ingredient : " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/ingredients/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteIngredient(@PathVariable Long id) {
        if (!ingredientService.exists(id)) {
            String msg = "ingredient with id=" + id + " not found!";
            log.warning("Error deleting ingredient: " + msg);
            throw new NotFoundException(msg);
        }
        try {
            ingredientService.deleteIngredient(id);
        } catch (Exception e) {
            log.warning("Error deleting ingredient : " + e.getMessage());
            throw e;
        }
    }
}
