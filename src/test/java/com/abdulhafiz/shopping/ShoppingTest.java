package com.abdulhafiz.shopping;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ShoppingTest {

    @InjectMocks
    private Shopping shopping;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCalculateGrossPrice() {
        Product apple = Product.builder().name("Apple").price(0.35).build();
        Item item1 = Item.builder().product(apple).quantity(2).build();

        Product banana = Product.builder().name("Banana").price(0.20).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();

        List<Item> items = Arrays.asList(item1, item2);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);
        BigDecimal expectedGrossPrice = BigDecimal.valueOf((0.35 * 2) + 0.20).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedGrossPrice, actualGrossPrice);
    }

    @Test
    public void testCalculateDiscountAmountNoDiscount() {
        Product apple = Product.builder().id("apple").name("Apple").price(0.35).build();
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Product banana = Product.builder().id("banana").name("Banana").price(0.20).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        List<Item> items = Arrays.asList(item1, item2);
        Basket basket = Basket.builder().basketItems(items).build();

        List<Discount> discountList = new ArrayList<>();
        discountList.add(MelonDiscount.builder().discountCode("MelonBUY1GET1").productId("melon").description("buy one get one free").active(true).build());
        discountList.add(LimeDiscount.builder().discountCode("Lime3For2Price").productId("lime").description("three for the price of two").active(true).build());

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, discountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);
        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualTotalDiscount);
        basket.setNetPrice(actualGrossPrice.subtract(actualTotalDiscount));

        BigDecimal expectedDiscount = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);
        BigDecimal expectedNetPrice = new BigDecimal("0.90").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }

    @Test
    public void testCalculateDiscountAmountMelonDiscount() {
        Product apple = Product.builder().id("apple").name("Apple").price(0.35).build();
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Product banana = Product.builder().id("banana").name("Banana").price(0.20).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item item3 = Item.builder().product(melon).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        List<Discount> discountList = new ArrayList<>();
        discountList.add(MelonDiscount.builder().discountCode("MelonBUY1GET1").productId("melon").description("buy one get one free").active(true).build());
        discountList.add(LimeDiscount.builder().discountCode("Lime3For2Price").productId("lime").description("three for the price of two").active(true).build());

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, discountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualTotalDiscount);
        basket.setNetPrice(actualGrossPrice.subtract(actualTotalDiscount));

        BigDecimal expectedDiscount = new BigDecimal("1.50").setScale(2, RoundingMode.CEILING);
        BigDecimal expectedNetPrice = new BigDecimal("2.90").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }

    @Test
    public void testCalculateDiscountAmountLimeDiscount() {
        Product apple = Product.builder().id("apple").name("Apple").price(0.35).build();
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Product banana = Product.builder().id("banana").name("Banana").price(0.20).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        Product lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item item3 = Item.builder().product(lime).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        List<Discount> discountList = new ArrayList<>();
        discountList.add(MelonDiscount.builder().discountCode("MelonBUY1GET1").productId("melon").description("buy one get one free").active(true).build());
        discountList.add(LimeDiscount.builder().discountCode("Lime3For2Price").productId("lime").description("three for the price of two").active(true).build());

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, discountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualTotalDiscount);
        basket.setNetPrice(actualGrossPrice.subtract(actualTotalDiscount));

        BigDecimal expectedDiscount = new BigDecimal("0.30").setScale(2, RoundingMode.CEILING);
        BigDecimal expectedNetPrice = new BigDecimal("1.65").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }

    @Test
    public void testCalculateTotalDiscountOfAmountAppleLimeMelon() {
        Product apple = Product.builder().id("apple").name("Apple").price(0.35).build();
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Product melon = Product.builder().id("melon").name("Melon").price(0.50).build();
        Item item2 = Item.builder().product(melon).quantity(7).build();
        Product lime = Product.builder().id("lime").name("Lime").price(0.15).build();
        Item item3 = Item.builder().product(lime).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        List<Discount> discountList = new ArrayList<>();
        discountList.add(MelonDiscount.builder().discountCode("MelonBUY1GET1").productId("melon").description("buy one get one free").active(true).build());
        discountList.add(LimeDiscount.builder().discountCode("Lime3For2Price").productId("lime").description("three for the price of two").active(true).build());

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, discountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualTotalDiscount);
        basket.setNetPrice(actualGrossPrice.subtract(actualTotalDiscount));

        BigDecimal expectedDiscount = new BigDecimal("1.80").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal expectedNetPrice = new BigDecimal("3.45").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }


}