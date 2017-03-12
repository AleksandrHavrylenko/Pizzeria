package com.xzteam.pizzeria.rest;

import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xzteam.pizzeria.api.BucketApi;
import com.xzteam.pizzeria.api.BucketApiListReply;
import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.mappers.BucketMapper;
import com.xzteam.pizzeria.services.BucketService;

@RestController
public class BucketController {
	private static final Logger logger = Logger.getLogger(ClientController.class.getName());
	
	@Autowired
	BucketService bucketService;

	@Autowired
	BucketMapper bucketMapper;

	@RequestMapping(path = "/bucket/all", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public BucketApiListReply getAllBuckets() {
		BucketApiListReply bucketList = new BucketApiListReply();
		bucketList.allBucket.addAll(bucketService.getAll()
				.stream()
				.map(bucket -> bucketMapper.toApi(bucket))
				.collect(Collectors.toList()));
		return bucketList;
	}

	@RequestMapping(path = "/bucket/byid/{id}", method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)	
	public BucketApiListReply getBucketById(@PathVariable Long id) {
		BucketApiListReply bucketById = new BucketApiListReply();
		bucketById.allBucket.add(bucketMapper.toApi(bucketService.getBucketById(id)));
		return bucketById;
	}

	@RequestMapping(path = "/bucket/dell/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public GenericReply delBucket(@PathVariable Long id) {
		GenericReply reply = new GenericReply();
		try{
		bucketService.deleteBucket(id);
		}catch(Exception e){
			reply.code = -1;
			reply.message = e.getMessage();
			logger.warning("Error deleting client: "+e.getMessage());
		}
		return reply;
	}
	
    @RequestMapping(path = "/bucket/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply addBucket(@RequestBody BucketApi req) {
        GenericReply rep = new GenericReply();
        try {
            Bucket bucket = bucketService.addBucket(bucketMapper.fromApi(req));
            rep.message = bucket.getId().toString();
        } catch (Exception e) {
            String msg = "Error adding dish: " + e.getMessage();
            rep.code = -1;
            rep.message = msg;
            logger.warning(msg);
        }
        return rep;
    }
}
