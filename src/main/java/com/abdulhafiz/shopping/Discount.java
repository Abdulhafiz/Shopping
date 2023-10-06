package com.abdulhafiz.shopping;

import java.math.BigDecimal;

public interface Discount {

    boolean isActive();

    String getProductId();

    BigDecimal getDiscountAmount(Item item);
}
