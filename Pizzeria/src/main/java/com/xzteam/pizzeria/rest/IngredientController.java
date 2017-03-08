package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.api.IngredientsApiListReply;
import com.xzteam.pizzeria.mappers.IngredientMapper;
import com.xzteam.pizzeria.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class IngredientController {

    private static final Logger logger = Logger.getLogger(IngredientController.class.getName());

    @Autowired
    IngredientService ingredientService;
    @Autowired
    IngredientMapper ingredientMapper;

    @RequestMapping(path = "/ingredients/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply getAllIngredients() {
        IngredientsApiListReply listReply = new IngredientsApiListReply();
        listReply.ingredients.addAll(ingredientService.getAll()
                .stream()
                .map(d -> ingredientMapper.toApi(d))
                .collect(Collectors.toList()));
        return listReply;
    }

    @RequestMapping(path = "/ingredients/byid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public IngredientsApiListReply getIngredientById(@PathVariable Long id) {
        IngredientsApiListReply reply = new IngredientsApiListReply();
        reply.ingredients.add(ingredientMapper.toApi(ingredientService.getIngredientById(id)));
        return reply;
    }

    @RequestMapping(path = "/ingredients/del/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delDish(@PathVariable Long id) {
        GenericReply rep = new GenericReply();
        rep.retcode = -1;
        rep.error_message = "Not implemented!";
        return rep;
    }
}
