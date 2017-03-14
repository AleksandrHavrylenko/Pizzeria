package com.xzteam.pizzeria.mappers;

import com.xzteam.pizzeria.api.BucketApi;
import com.xzteam.pizzeria.api.BucketApiInfo;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.repository.BucketRepository;
import com.xzteam.pizzeria.repository.DishRepository;
import com.xzteam.pizzeria.repository.PizzaRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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

    public BucketApi toApi(Bucket bucket) {
        BucketApiInfo bucketApi = null;
        if (bucket != null) {
            bucketApi = new BucketApiInfo();
            bucketApi.id = bucket.getId().toString();
            bucketApi.address = bucket.getAddress();
            bucketApi.price = bucket.getPrice();
            bucketApi.date = bucket.getDate();

            bucketApi.dishes.addAll(dishRepository.findAll()
                    .stream()
                    .filter(dish -> dish.getItem() != null && dish.getItem().getBucket().equals(bucket))
                    .map(d -> dishMapper.toApi(d))
                    .collect(Collectors.toList()));

            bucketApi.pizzas.addAll(pizzaRepository.findAll()
                    .stream()
                    .filter(pizza -> pizza.getItem() != null && pizza.getItem().getBucket().equals(bucket))
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

    public Bucket fromApi(BucketApi api) {
        Bucket bucket = null;
        if (api.id != null) {
            bucket = bucketRepository.findOne(Long.parseLong(api.id));
        }
        if (bucket == null) {
            bucket = newBucket();
        }
        bucket.setAddress(api.address);
        bucket.setDate(api.date);
        bucket.setPrice(api.price);
        return bucket;
    }
}
