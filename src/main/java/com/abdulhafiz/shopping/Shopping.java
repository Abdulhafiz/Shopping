package com.abdulhafiz.shopping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Shopping implements ShoppingService {
    /**
     * @param basket all the items
     * @return gross price
     */
    @Override
    public BigDecimal calculateGrossPrice(Basket basket) {

       return BigDecimal.valueOf(basket.getBasketItems().stream()
                       .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                       .reduce(0, Double::sum))
               .setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     *
     * @param basket all the items
     * @param discountList all the discount retrieved from database for example
     * @return total discount
     */
    @Override
    public BigDecimal calculateTotalDiscount(Basket basket, List<Discount> discountList) {
        return basket.getBasketItems().stream()
                .map(item -> discountList.stream()
                        .filter(discount -> discount.getProductId().equals(item.getProduct().getId()) && discount.isActive())
                        .findAny()
                        .map(discount -> discount.getDiscountAmount(item))
                        .orElse(BigDecimal.ZERO)
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.CEILING);
    }
}
