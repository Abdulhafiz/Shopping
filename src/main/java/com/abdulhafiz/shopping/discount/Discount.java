package com.abdulhafiz.shopping.discount;

import com.abdulhafiz.shopping.basket.Item;

import java.math.BigDecimal;

public interface Discount {

    String getDiscountCode();

    BigDecimal getDiscountAmount(Item item);
}
