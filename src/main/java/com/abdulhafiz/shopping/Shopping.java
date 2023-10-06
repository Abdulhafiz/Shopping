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
               .reduce(0, Double::sum)).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     *
     * @param basket all the items
     * @param discountList all the discount retrieved from database for example
     * @return total discount
     */
    @Override
    public BigDecimal calculateTotalDiscount(Basket basket, List<Discount> discountList) {
        BigDecimal totalDiscount = new BigDecimal("0.00");

        for(Item item: basket.getBasketItems()){
            Discount discount = discountList.stream()
                    .filter(d -> d.getProductId().equalsIgnoreCase(item.getProduct().getId()) && d.isActive())
                    .findAny()
                    .orElse(null);
            if (discount != null){
                totalDiscount = totalDiscount.add(discount.getDiscountAmount(item));
            }
        }
        return totalDiscount.setScale(2, RoundingMode.CEILING);
    }
}
