package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.BucketApi;
import com.xzteam.pizzeria.api.BucketApiListReply;
import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.mappers.BucketMapper;
import com.xzteam.pizzeria.services.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class BucketController {
    private static final Logger log = Logger.getLogger(BucketController.class.getName());

    @Autowired
    BucketService bucketService;
    @Autowired
    BucketMapper bucketMapper;

    @RequestMapping(path = "/buckets/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getAllBuckets() {
        BucketApiListReply bucketList = new BucketApiListReply();
        bucketList.allBucket.addAll(bucketService.getAll()
                .stream()
                .map(bucket -> bucketMapper.toApi(bucket))
                .collect(Collectors.toList()));
        return bucketList;
    }

    @RequestMapping(path = "/buckets/byid/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getBucketById(@PathVariable Long id) {
        BucketApiListReply bucketById = new BucketApiListReply();
        bucketById.allBucket.add(bucketMapper.toApi(bucketService.getBucketById(id)));
        return bucketById;
    }

    @RequestMapping(path = "/buckets/del/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply delBucket(@PathVariable Long id) {
        GenericReply reply = new GenericReply();
        try {
            bucketService.deleteBucket(id);
        } catch (Exception e) {
            reply.code = -1;
            reply.message = e.getMessage();
            log.warning("Error deleting bucket: " + e.getMessage());
        }
        return reply;
    }

    @RequestMapping(path = "/buckets/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericReply addBucket(@RequestBody BucketApi req) {
        GenericReply rep = new GenericReply();
        try {
            Bucket bucket = bucketService.addBucket(bucketMapper.fromApi(req));
            rep.message = bucket.getId().toString();
        } catch (Exception e) {
            String msg = "Error adding bucket: " + e.getMessage();
            rep.code = -1;
            rep.message = msg;
            log.warning(msg);
        }
        return rep;
    }
}
