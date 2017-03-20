package com.xzteam.pizzeria.api.bucket;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BucketApiAddRequest extends BucketApi {

    @NotNull
    public Long clientId;
    public List<Long> dishesIds = new ArrayList<>();
    public List<Long> pizzasIds = new ArrayList<>();
}
