package com.abdulhafiz.shopping.discount.depricated;

import com.abdulhafiz.shopping.discount.Discount;
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
public class LimeDiscount implements Discount {

    private String discountCode;
    private String description;
    private String productId;
    private boolean active;

    /**
     * Limes discount = lime-discount int(quanty/3) * (price 0.15) = int(7/3) * 0.15 = 2 * 0.15 = 0.30
     * @param item one item with quantity(s)
     * @return discount amount for one item
     */
    @Override
    public BigDecimal getDiscountAmount(Item item) {
        int quantity = (item.getQuantity()/3);
        double discountPrice= quantity * item.getProduct().getPrice();

        return new BigDecimal(discountPrice).setScale(2, RoundingMode.CEILING);
    }
}
