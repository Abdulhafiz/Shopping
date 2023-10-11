package com.abdulhafiz.shopping;

import com.abdulhafiz.shopping.basket.Item;
import com.abdulhafiz.shopping.basket.Product;
import com.abdulhafiz.shopping.discount.Buy1Get1FreeDiscount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class Buy1Get1FreeDiscountTest {

    Buy1Get1FreeDiscount discount = Buy1Get1FreeDiscount.builder().discountCode("BUY1GET1").build();
    @Test
    public void testCalculateMelonDiscountFor1Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(1).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(melonItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.50").setScale(2, RoundingMode.CEILING);

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
        BigDecimal expectedDiscountAmount = new BigDecimal("1.00").setScale(2, RoundingMode.CEILING);

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
        BigDecimal expectedGrossPrice = BigDecimal.valueOf(1.50).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedGrossPrice, actualGrossPrice);

    }

    @Test
    public void testCalculateMelonDiscountFor6Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(6).build();

        BigDecimal actualGrossPrice = discount.getDiscountAmount(melonItem);
        BigDecimal expectedGrossPrice = BigDecimal.valueOf(1.50).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedGrossPrice, actualGrossPrice);

    }

    @Test
    public void testCalculateMelonDiscountFor7Items() {
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item melonItem = Item.builder().product(melon).quantity(7).build();

        BigDecimal actualGrossPrice = discount.getDiscountAmount(melonItem);
        BigDecimal expectedGrossPrice = BigDecimal.valueOf(2.00).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedGrossPrice, actualGrossPrice);

    }
}