package com.xzteam.pizzeria.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.domain.Client;
import com.xzteam.pizzeria.repository.BucketRepository;

@Service
public class BucketService {
	@Autowired
	BucketRepository bucketRepository;
	
	public List<Bucket> getAllBucketByClient(Client client){
		return bucketRepository.findAllBucketByClient(client);
	}
	
	public Bucket getBucketById(Long id){
		Bucket bucket = bucketRepository.findOne(id);
		return bucket;
	}
	
	public List<Bucket> findAll(){
		return bucketRepository.findAll();
	}
}
