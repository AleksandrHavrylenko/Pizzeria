package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BucketService {
    private static final Logger log = Logger.getLogger(BucketService.class.getName());
    @Autowired
    BucketRepository bucketRepository;

    public Bucket getBucketById(Long id) {
        return bucketRepository.findOne(id);
    }

    public List<Bucket> getAll() {
        return bucketRepository.findAll();
    }

    public List<Bucket> getAllBucketsByClient(Client client) {
        return bucketRepository.findAllBucketByClient(client);
    }

    public Bucket addBucket(Bucket bucket) {
        log.info("Adding bucket with id %s" + bucket.getId());
        return bucketRepository.save(bucket);
    }

    public void deleteBucket(Long id) {
        Bucket bucket = bucketRepository.findOne(id);
        if (bucket != null) {
            log.info("Deleting bucket with id %s" + bucket.getId());
            bucketRepository.delete(bucket);
        }
    }
}
