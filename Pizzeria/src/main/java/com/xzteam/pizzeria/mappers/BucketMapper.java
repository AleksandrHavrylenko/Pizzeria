package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.BucketApi;
import com.xzteam.pizzeria.api.BucketApiAddRequest;
import com.xzteam.pizzeria.api.BucketApiInfo;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Dish;
import com.xzteam.pizzeria.domain.Pizza;
import com.xzteam.pizzeria.repository.BucketRepository;
import com.xzteam.pizzeria.repository.ClientRepository;
import com.xzteam.pizzeria.repository.DishRepository;
import com.xzteam.pizzeria.repository.PizzaRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class BucketMapper {

    @Autowired
    BucketRepository bucketRepository;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    DishMapper dishMapper;
    @Autowired
    PizzaMapper pizzaMapper;
    @Autowired
    ClientRepository clientRepository;

    public BucketApi toApi(Bucket bucket) {
        BucketApiInfo bucketApi = null;
        if (bucket != null) {
            bucketApi = new BucketApiInfo();
            bucketApi.id = bucket.getId().toString();
            bucketApi.address = bucket.getAddress();
            bucketApi.price = bucket.getPrice();
            bucketApi.date = bucket.getDate();
            bucketApi.status = bucket.getStatus();

            bucketApi.dishes.addAll(dishRepository.findAllDishesByBuckets(bucket)
                    .stream()
                    .map(d -> dishMapper.toApi(d))
                    .collect(Collectors.toList()));

            bucketApi.pizzas.addAll(pizzaRepository.findAllPizzasByBuckets(bucket)
                    .stream()
                    .map(p -> pizzaMapper.toApi(p))
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
            idOK = !bucketRepository.exists(id);
        }
        bucket.setId(id);
        return bucket;
    }

    public Bucket fromApi(BucketApiAddRequest api) {
        Bucket bucket = null;
        if (api.id != null) {
            bucket = bucketRepository.findOne(Long.parseLong(api.id));
        }
        if (bucket == null) {
            bucket = newBucket();
        }
        Client client = api.clientId == null ? null : clientRepository.findOne(api.clientId);
        if (client == null) {
            return null;
        }
        bucket.setAddress(api.address);
        bucket.setDate(api.date);
        bucket.setPrice(api.price);
        bucket.setClient(client);

        List<Dish> dishes = api.dishesIds.stream()
                .map(id -> dishRepository.findOne(id))
                .collect(Collectors.toList());
        bucket.setDishes(dishes);

        List<Pizza> pizzas = api.pizzasIds.stream()
                .map(id -> pizzaRepository.findOne(id))
                .collect(Collectors.toList());
        bucket.setPizzas(pizzas);

        return bucket;
    }
}
