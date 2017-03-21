package com.xzteam.pizzeria.rest;

import com.xzteam.pizzeria.api.bucket.BucketApiAddRequest;
import com.xzteam.pizzeria.api.bucket.BucketApiListReply;
import com.xzteam.pizzeria.domain.Bucket;
import com.xzteam.pizzeria.mappers.BucketMapper;
import com.xzteam.pizzeria.rest.exceptions.NotFoundException;
import com.xzteam.pizzeria.services.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class BucketController {
    private static final Logger log = Logger.getLogger(BucketController.class.getName());

    @Autowired
    private BucketService bucketService;
    @Autowired
    private BucketMapper bucketMapper;

    @RequestMapping(path = "/buckets", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getAllBuckets() {
        BucketApiListReply bucketList = new BucketApiListReply();
        bucketList.buckets.addAll(bucketService.getAll()
                .stream()
                .map(bucket -> bucketMapper.toApiGet(bucket))
                .collect(Collectors.toList()));
        return bucketList;
    }

    @RequestMapping(path = "/buckets/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getBucketById(@PathVariable Long id) {
        BucketApiListReply bucketById = new BucketApiListReply();
        bucketById.buckets.add(bucketMapper.toApiGet(bucketService.getBucketById(id)));
        return bucketById;
    }

    @RequestMapping(path = "/buckets/byClientId/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply getBucketsByClientId(@PathVariable Long id) {
        BucketApiListReply bucketList = new BucketApiListReply();
        bucketList.buckets.addAll(bucketService.getAllBucketsByClientId(id)
                .stream()
                .map(bucket -> bucketMapper.toApiGet(bucket))
                .collect(Collectors.toList()));
        return bucketList;
    }

    @RequestMapping(path = "/buckets", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply addBucket(@RequestBody BucketApiAddRequest req) {
        BucketApiListReply reply = new BucketApiListReply();
        try {
            Bucket bucket = bucketService.addBucket(bucketMapper.fromApiPost(req));
            reply.buckets.add(bucketMapper.toApiGet(bucket));
        } catch (Exception e) {
            log.warning("Error adding bucket: " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/buckets/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BucketApiListReply updateBucket(@PathVariable Long id, @Valid @RequestBody BucketApiAddRequest req) {
        if (!bucketService.exists(id)) {
            String msg = "Bucket with id=" + id + " not found!";
            log.warning("Error updating bucket: " + msg);
            throw new NotFoundException(msg);
        }
        BucketApiListReply reply = new BucketApiListReply();
        try {
            Bucket bucket = bucketService.updateBucket(bucketMapper.fromApiPut(req, id));
            reply.buckets.add(bucketMapper.toApiGet(bucket));
        } catch (Exception e) {
            log.warning("Error updating bucket : " + e.getMessage());
            throw e;
        }
        return reply;
    }

    @RequestMapping(path = "/buckets/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteBucket(@PathVariable Long id) {
        if (!bucketService.exists(id)) {
            String msg = "Bucket with id=" + id + " not found!";
            log.warning("Error deleting bucket: " + msg);
            throw new NotFoundException(msg);
        }
        try {
            bucketService.deleteBucket(id);
        } catch (Exception e) {
            log.warning("Error deleting bucket : " + e.getMessage());
            throw e;
        }
    }

}
