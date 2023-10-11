package com.abdulhafiz.shopping;

import com.abdulhafiz.shopping.basket.Basket;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingService {
    BigDecimal calculateGrossPrice(Basket basket);

    BigDecimal calculateTotalDiscount(Basket basket, List<ProductDiscount> discountList);
}
