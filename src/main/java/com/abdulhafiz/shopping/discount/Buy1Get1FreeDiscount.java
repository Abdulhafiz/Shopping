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
public class Buy1Get1FreeDiscount implements Discount{

    private String discountCode;
    private String description;
    private int quantity;
    private double price;


    /**
     * Melon discount = free product quantity * price
     * @param item with one product with quantity(s)
     * @return total discount
     */
    @Override
    public BigDecimal getDiscountAmount(Item item) {
        var freeItems = item.getQuantity()/2 ;
        var discountPrice= ( item.getQuantity() * item.getProduct().getPrice()) - (freeItems * item.getProduct().getPrice());
        return new BigDecimal(discountPrice).setScale(2, RoundingMode.CEILING);
    }
}
