package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.DishApiListReply;
import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.mappers.DishMapper;
import com.xzteam.pizzeria.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class DishController {

    private static final Logger logger = Logger.getLogger(DishController.class.getName());

    @Autowired
    DishService dishService;
    @Autowired
    DishMapper dishMapper;

    @RequestMapping(path = "/dishes/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply getAllDishes() {
        DishApiListReply dishApiReply = new DishApiListReply();
        dishApiReply.dishApi.addAll(dishService.getAll()
                .stream()
                .map(d -> dishMapper.toApi(d))
                .collect(Collectors.toList()));
        return dishApiReply;
    }

    @RequestMapping(path="/dishes/byid/{id}",  method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DishApiListReply getDishById(@PathVariable Long id ){
        DishApiListReply reply = new DishApiListReply();
        reply.dishApi.add(dishMapper.toApi(dishService.getDishById(id)));
        return reply;
    }

    @RequestMapping(path="/dishes/del/{id}",  method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delDish(@PathVariable Long id ){
        GenericReply rep = new GenericReply();
        try{
            dishService.deleteDish(id);
        }catch(Exception e){
            rep.retcode = -1;
            rep.error_message = e.getMessage();
            logger.warning("Error deleting dish: "+e.getMessage());
        }
        return rep;
    }
}
