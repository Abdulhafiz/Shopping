package com.abdulhafiz.shopping;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MelonDiscountTest {

    @InjectMocks
    MelonDiscount discount;

    @Test
    public void testCalculateMelonDiscountFor1Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(1).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(melonItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateMelonDiscountFor2Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(2).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(melonItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.50").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateMelonDiscountFor3Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(3).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(melonItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.50").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateMelonDiscountFor4Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(4).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(melonItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0.50 * 2).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateMelonDiscountFor5Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(5).build();

        BigDecimal actualGrossPrice = discount.getDiscountAmount(melonItem);
        BigDecimal expectedGrossPrice = BigDecimal.valueOf(0.50 * 2).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedGrossPrice, actualGrossPrice);

    }

}