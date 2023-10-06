package com.abdulhafiz.shopping;

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
public class MelonDiscount implements Discount{

    private String discountCode;
    private String description;
    private String productId;
    private boolean active;

    /**
     * Melon discount = free product quantity * price
     * @param item with one product with quantity(s)
     * @return total discount
     */
    @Override
    public BigDecimal getDiscountAmount(Item item) {
        var freeItems = item.getQuantity()/2 ;
        var discountPrice= freeItems * item.getProduct().getPrice();
        return new BigDecimal(discountPrice).setScale(2, RoundingMode.CEILING);
    }
}
