package com.xzteam.pizzeria.api.bucket;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BucketApiAddRequest extends BucketApi {

    public final List<Long> dishesIds = new ArrayList<>();
    public final List<Long> pizzasIds = new ArrayList<>();
    @NotNull
    public Long clientId;
}
