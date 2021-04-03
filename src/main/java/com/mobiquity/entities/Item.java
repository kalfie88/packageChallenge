package com.mobiquity.entities;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;

@Value
@Builder
public class Item {

    int id;

    @Max(value = 100, message = "Weight must be <= 100")
    double weight;

    @Max(value = 100, message = "Cost must be <= 100")
    int cost;
}
