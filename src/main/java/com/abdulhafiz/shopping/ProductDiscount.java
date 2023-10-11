package com.abdulhafiz.shopping;

import com.abdulhafiz.shopping.basket.Product;
import com.abdulhafiz.shopping.discount.Discount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDiscount {

    private String productDiscountId;
    private Discount discount;
    private Product product;
    private boolean active;
}
