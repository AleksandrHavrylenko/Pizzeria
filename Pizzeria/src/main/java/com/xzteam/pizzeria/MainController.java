package com.xzteam.pizzeria;

import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.repository.BucketRepository;
import com.xzteam.pizzeria.repository.ClientRepository;
import com.xzteam.pizzeria.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class.getName());

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BucketRepository bucketRepository;

    public void showAllDishes() {
        dishRepository.findAll().forEach(dish -> logger.log(Level.INFO, dish.toString()));
        Dish dish = dishRepository.findOne(777L);
        System.out.println(dish.getItem().toString());
    }

    public void showTests() {
        Client client = clientRepository.findOne(1L);
        List<Bucket> list = bucketRepository.findAllBucketByClient(client);
        System.out.println(Arrays.toString(list.toArray()));
        //System.out.println(client.toString() +"\n" + Arrays.toString(client.getBuckets().toArray()));
    }
}
