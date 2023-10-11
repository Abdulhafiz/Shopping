package com.abdulhafiz.shopping.basket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {
    private Product product;
    private int quantity;

}
