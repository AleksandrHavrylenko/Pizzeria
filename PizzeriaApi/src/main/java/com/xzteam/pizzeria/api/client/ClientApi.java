package com.xzteam.pizzeria.api.client;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ClientApi {

    public String id;

    @Email
    public String email;

    public String passHash;

    @NotNull
    @Size(min = 1, max = 50)
    public String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    public String lastName;

    @NotNull
    @Size(min = 11, max = 11)
    public String phone;

    public Float spentMoney;

}
