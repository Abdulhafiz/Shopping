package com.abdulhafiz.shopping.discount;

import com.abdulhafiz.shopping.basket.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Buy3BeerFor5PoundDiscount implements Discount{

    private String discountCode;
    private String description;
    private int discountQuantity;
    private double discountPrice;


    /**
     * Limes discount = lime-discount int(quanty/3) * (price 0.15) = int(7/3) * 0.15 = 2 * 0.15 = 0.30
     * @param item one item with quantity(s)
     * @return discount amount for one item
     */
    @Override
    public BigDecimal getDiscountAmount(Item item) {
        int fullSetsOfBeers = item.getQuantity()/ getDiscountQuantity();
        int remainingBeers = item.getQuantity() % getDiscountQuantity();
        double discountPrice= fullSetsOfBeers * getDiscountPrice() + (remainingBeers*item.getProduct().getPrice());

        return new BigDecimal(discountPrice).setScale(2, RoundingMode.CEILING);
    }
}
