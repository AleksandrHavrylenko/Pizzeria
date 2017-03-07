package com.xzteam.pizzeria.utils;

import static java.lang.Math.abs;
import static java.util.UUID.randomUUID;

public class EntityIdGenerator {
    public static long random() {
        return abs(randomUUID().getLeastSignificantBits());
    }
}
