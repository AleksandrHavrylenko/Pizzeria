package com.xzteam.pizzeria.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.xzteam.pizzeria.api.BucketApi;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.repository.BucketRepository;
import com.xzteam.pizzeria.utils.EntityIdGenerator;

@Component
public class BucketMapper {

	@Autowired
	BucketRepository bucketRepository;

	public BucketApi toApi(Bucket bucket) {
		BucketApi bucketApi = null;
		if(bucket != null){
			bucketApi = new BucketApi();
			bucketApi.id = bucket.getId();
			bucketApi.address = bucket.getAddress();
			bucketApi.price = bucket.getPrice();
			bucketApi.date = bucket.getDate();
		}
		return bucketApi;
	}

	private Bucket newBucket() {
		Bucket au = new Bucket();
		boolean idOK = false;
		Long id = 0L;
		while (!idOK) {
			id = EntityIdGenerator.random();
			idOK = !bucketRepository.exists(id);
		}
		au.setId(id);
		return au;
	}

	public Bucket fromApi(BucketApi api) {
		Bucket bucket = null;
		if (api.id != null) {
			bucket = bucketRepository.findOne(api.id);
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
