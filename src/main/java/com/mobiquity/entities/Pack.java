package com.mobiquity.entities;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Pack {

    int capacity;
    List<Item> itemsToChoose;

}
