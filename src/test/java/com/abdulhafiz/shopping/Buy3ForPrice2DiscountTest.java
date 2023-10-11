package com.abdulhafiz.shopping;

import com.abdulhafiz.shopping.basket.Item;
import com.abdulhafiz.shopping.basket.Product;
import com.abdulhafiz.shopping.discount.Buy3ForPrice2Discount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class Buy3ForPrice2DiscountTest {

    Buy3ForPrice2Discount discount = Buy3ForPrice2Discount.builder().discountCode("BUY3FOR2PRICE")
            .description("three for the price of two")
            .quantity(3)
            .price(0.30).build();


    @Test
    public void testCalculateLimeDiscountFor1Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(1).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.15").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor2Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(2).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.30").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor3Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(3).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.30").setScale(2, RoundingMode.CEILING);

        assertEquals(expectedDiscountAmount, actualDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor4Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(4).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.45").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);
    }

    @Test
    public void testCalculateLimeDiscountFor5Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(5).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = new BigDecimal("0.60").setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }
    @Test
    public void testCalculateLimeDiscountFor6Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(6).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0.60).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    public void testCalculateLimeDiscountFor7Items() {
        Product Lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item LimeItem = Item.builder().product(Lime).quantity(7).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(LimeItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(0.75).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }
}