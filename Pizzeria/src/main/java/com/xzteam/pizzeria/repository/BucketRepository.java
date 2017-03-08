package com.xzteam.pizzeria.repository;

import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketRepository extends CrudRepository<Bucket, Long> {
    List<Bucket> findAllBucketByClient(Client client);
    List<Bucket> findAll();
}
