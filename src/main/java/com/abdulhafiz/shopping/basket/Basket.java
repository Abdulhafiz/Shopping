package com.abdulhafiz.shopping.basket;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class Basket {

    private List<Item> basketItems;
    private BigDecimal grossPrice;
    private BigDecimal totalDiscount;
    private BigDecimal netPrice;
}
