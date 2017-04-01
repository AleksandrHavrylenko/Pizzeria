package com.xzteam.pizzeria.services;

import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.repository.BucketRepository;
import com.xzteam.pizzeria.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class BucketService {
    private static final Logger log = Logger.getLogger(BucketService.class.getName());
    @Autowired
    private BucketRepository bucketRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Bucket getBucketById(Long id) {
        return bucketRepository.findOne(id);
    }

    public List<Bucket> getAll() {
        return bucketRepository.findAll();
    }

    public List<Bucket> getAllBucketsByClientId(Long id) {
        Client client = clientRepository.findOne(id);
        return client == null ? Collections.EMPTY_LIST : bucketRepository.findAllBucketByClient(client);
    }

    public Bucket addBucket(Bucket bucket) {
        log.info("Adding bucket with id " + bucket.getId());
        return bucketRepository.save(bucket);
    }

    public Bucket updateBucket(Bucket bucket) {
        log.info("Updating bucket with id " + bucket.getId());
        return bucketRepository.save(bucket);
    }

    public void deleteBucket(Long id) {
        Bucket bucket = bucketRepository.findOne(id);
        if (bucket != null) {
            log.info("Deleting bucket with id " + bucket.getId());
            bucketRepository.delete(bucket);
        }
    }

    public boolean exists(Long id) {
        return bucketRepository.exists(id);
    }
}
