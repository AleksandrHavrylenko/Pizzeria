package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.domain.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    List<Pizza> findAll();

    List<Pizza> findAllPizzasByBuckets(Bucket bucket);

    List<Pizza> findAllPizzasByClient(Client client);
}
