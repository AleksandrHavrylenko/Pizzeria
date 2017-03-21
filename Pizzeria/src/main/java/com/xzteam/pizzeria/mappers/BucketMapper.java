package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.bucket.BucketApi;
import com.xzteam.pizzeria.api.bucket.BucketApiAddRequest;
import com.xzteam.pizzeria.api.bucket.BucketApiInfo;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.rest.exceptions.BadRequestException;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.BucketService;
import com.xzteam.pizzeria.services.ClientService;
import com.xzteam.pizzeria.services.DishService;
import com.xzteam.pizzeria.services.PizzaService;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class BucketMapper {

    @Autowired
    private BucketService bucketService;
    @Autowired
    private DishService dishService;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private PizzaMapper pizzaMapper;

    public BucketApi toApiGet(Bucket bucket) {
        BucketApiInfo bucketApi = null;
        if (bucket != null) {
            bucketApi = new BucketApiInfo();
            bucketApi.id = bucket.getId().toString();
            bucketApi.address = bucket.getAddress();
            bucketApi.price = bucket.getPrice();
            bucketApi.date = bucket.getDate();
            bucketApi.status = bucket.getStatus();

            bucketApi.dishes.addAll(dishService.getAllDishesInBucket(bucket)
                    .stream()
                    .map(d -> dishMapper.toApiGet(d))
                    .collect(Collectors.toList()));

            bucketApi.pizzas.addAll(pizzaService.getAllPizzasInBucket(bucket)
                    .stream()
                    .map(p -> pizzaMapper.toApiGet(p))
                    .collect(Collectors.toList()));
        }
        return bucketApi;
    }

    private Bucket newBucket() {
        Bucket bucket = new Bucket();
        boolean idOK = false;
        Long id = 0L;
        while (!idOK) {
            id = EntityIdGenerator.random();
            idOK = !bucketService.exists(id);
        }
        bucket.setId(id);
        return bucket;
    }

    private void updateFields(Bucket bucket, BucketApiAddRequest api) {
        Client client = api.clientId == null ? null : clientService.getClientById(api.clientId);
        if (client == null) {
            throw new BadRequestException("Bucket must have a minimum one dish or pizza!");
        }
        if (api.dishesIds.isEmpty() && api.pizzasIds.isEmpty()) {
            throw new BadRequestException("Bucket must be have a client creator id!");
        }

        bucket.setAddress(api.address);
        bucket.setDate(api.date);
        bucket.setPrice(api.price);
        bucket.setClient(client);

        List<Dish> dishes = api.dishesIds.stream()
                .map(id -> dishService.getDishById(id))
                .collect(Collectors.toList());
        bucket.setDishes(dishes);

        List<Pizza> pizzas = api.pizzasIds.stream()
                .map(id -> pizzaService.getPizzaById(id))
                .collect(Collectors.toList());
        bucket.setPizzas(pizzas);
    }

    public Bucket fromApiPost(BucketApiAddRequest api) {
        Bucket bucket = newBucket();
        updateFields(bucket, api);
        return bucket;
    }

    public Bucket fromApiPut(BucketApiAddRequest api, long id) throws NotFoundException {
        Bucket bucket = bucketService.getBucketById(id);
        if (bucket == null) {
            throw new NotFoundException();
        }
        updateFields(bucket, api);
        return bucket;
    }

}
