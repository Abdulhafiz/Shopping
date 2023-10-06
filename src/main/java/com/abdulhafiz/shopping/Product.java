package com.abdulhafiz.shopping;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private String id;
    private String name;
    private double price;

}
