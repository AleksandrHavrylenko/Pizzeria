package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.GenericReply;
import com.xzteam.pizzeria.api.bucket.BucketApiAddRequest;
import com.xzteam.pizzeria.api.bucket.BucketApiListReply;
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

    @RequestMapping(path = "/buckets", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getAllBuckets() {
        BucketApiListReply bucketList = new BucketApiListReply();
        bucketList.buckets.addAll(bucketService.getAll()
                .stream()
                .map(bucket -> bucketMapper.toApi(bucket))
                .collect(Collectors.toList()));
        return bucketList;
    }

    @RequestMapping(path = "/buckets/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getBucketById(@PathVariable Long id) {
        BucketApiListReply bucketById = new BucketApiListReply();
        bucketById.buckets.add(bucketMapper.toApi(bucketService.getBucketById(id)));
        return bucketById;
    }

    @RequestMapping(path = "/buckets/byClientId/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getBucketsByClientId(@PathVariable Long id) {
        BucketApiListReply bucketList = new BucketApiListReply();
        bucketList.buckets.addAll(bucketService.getAllBucketsByClientId(id)
                .stream()
                .map(bucket -> bucketMapper.toApi(bucket))
                .collect(Collectors.toList()));
        return bucketList;
    }

    @RequestMapping(path = "/buckets/{id}", method = RequestMethod.DELETE,
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

    @RequestMapping(path = "/buckets", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply addBucket(@RequestBody BucketApiAddRequest req) {
        BucketApiListReply reply = new BucketApiListReply();
        try {
            Bucket bucket = bucketService.addBucket(bucketMapper.fromApi(req));
            reply.buckets.add(bucketMapper.toApi(bucket));
        } catch (Exception e) {
            String msg = "Error adding bucket: " + e.getMessage();
            reply.code = -1;
            reply.message = msg;
            log.warning(msg);
    }
        return reply;
    }

}
