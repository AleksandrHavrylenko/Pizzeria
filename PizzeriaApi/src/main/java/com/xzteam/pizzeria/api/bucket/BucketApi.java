package com.xzteam.pizzeria.api.bucket;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;

public abstract class BucketApi {

    public String id;

    @NotNull
    @Size(min = 1, max = 200)
    public String address;

    @NotNull
    @Min(0)
    public Float price;

    public Calendar date;

    public Boolean status;
}
