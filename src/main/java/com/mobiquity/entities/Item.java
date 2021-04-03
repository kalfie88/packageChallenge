package com.mobiquity.entities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Item {

    int id;
    double weight;
    int value;
}
