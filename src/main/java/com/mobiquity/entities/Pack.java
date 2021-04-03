package com.mobiquity.entities;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

@Value
@Builder
public class Pack {

    @Max(value = 100, message = "Capacity must be <= 100")
    int capacity;

    @Size(max = 15, message = "Items must be <= 15")
    List<Item> itemsToChoose;

}
