package com.xzteam.pizzeria.api.bucket;

import com.xzteam.pizzeria.api.GenericReply;

import java.util.ArrayList;
import java.util.List;

public class BucketApiListReply extends GenericReply {
    public List<BucketApi> buckets = new ArrayList<>();
}
