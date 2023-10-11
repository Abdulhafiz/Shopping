package com.abdulhafiz.shopping.depricated;

import com.abdulhafiz.shopping.basket.Item;
import com.abdulhafiz.shopping.basket.Product;
import com.abdulhafiz.shopping.discount.depricated.LimeDiscount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LimeDiscountTest {

    @InjectMocks
    LimeDiscount discount;

    @Test
    public void testCalculateLimeDiscountFor1Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(1).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor2Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(2).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor3Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(3).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.15").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor4Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(4).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.15").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor5Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(5).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.15").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }
    @Test
    public void testCalculateLimeDiscountFor6Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(6).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0.15 * 2).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    public void testCalculateLimeDiscountFor7Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(7).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0.15 * 2).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }


}