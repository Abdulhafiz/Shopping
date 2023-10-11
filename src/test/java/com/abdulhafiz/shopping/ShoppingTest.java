package com.abdulhafiz.shopping;

import com.abdulhafiz.shopping.basket.Basket;
import com.abdulhafiz.shopping.basket.Item;
import com.abdulhafiz.shopping.basket.Product;
import com.abdulhafiz.shopping.discount.Buy1Get1FreeDiscount;
import com.abdulhafiz.shopping.discount.Buy3BeerFor5PoundDiscount;
import com.abdulhafiz.shopping.discount.Buy3ForPrice2Discount;
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

    List<ProductDiscount> productDiscountList;
    Product apple;
    Product banana;
    Product melon;
    Product lime;
    Product beer;

    @BeforeEach
    void setUp() {
        productDiscountList = new ArrayList<>();
        apple = Product.builder().id("apple01").name("Apple").price(0.35).build();
        melon = Product.builder().id("melon01").name("Melon").price(0.50).build();
        lime = Product.builder().id("lime01").name("Lime").price(0.15).build();
        banana = Product.builder().name("Banana").price(0.20).build();
        beer = Product.builder().id("beer01").name("Beer").price(2.00).build();
        Buy1Get1FreeDiscount buy1Get1FreeDiscount = Buy1Get1FreeDiscount.builder().discountCode("BUY1GET1").description("buy one get one free").build();
        Buy3ForPrice2Discount buy3FOR2PRICE = Buy3ForPrice2Discount.builder().discountCode("BUY3FOR2PRICE").quantity(3).description("three for the price of two").build();
        Buy3BeerFor5PoundDiscount buy3For5Pound = Buy3BeerFor5PoundDiscount.builder().discountCode("BUY3BEERFOR5POUND").discountQuantity(3).discountPrice(5.00).description("three beer for the price of £5").build();
        productDiscountList.add(ProductDiscount.builder().product(melon).discount(buy1Get1FreeDiscount).active(true).build());
        productDiscountList.add(ProductDiscount.builder().product(lime).discount(buy3FOR2PRICE).active(true).build());
        productDiscountList.add(ProductDiscount.builder().product(beer).discount(buy3For5Pound).active(true).build());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCalculateGrossPrice() {
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        List<Item> items = Arrays.asList(item1, item2);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);
        BigDecimal expectedGrossPrice = BigDecimal.valueOf((0.35 * 2) + 0.20).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedGrossPrice, actualGrossPrice);
    }

    @Test
    public void testCalculateDiscountAmountNoDiscount() {
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        List<Item> items = Arrays.asList(item1, item2);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, productDiscountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);
        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualGrossPrice.subtract(actualTotalDiscount));
        basket.setNetPrice(actualTotalDiscount);

        BigDecimal expectedDiscount = new BigDecimal("0.00").setScale(2, RoundingMode.CEILING);
        BigDecimal expectedNetPrice = new BigDecimal("0.90").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }

    @Test
    public void testCalculateDiscountAmountMelonDiscount() {
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        Item item3 = Item.builder().product(melon).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, productDiscountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualGrossPrice.subtract(actualTotalDiscount));
        basket.setNetPrice(actualTotalDiscount);

        BigDecimal expectedDiscount = new BigDecimal("1.50").setScale(2, RoundingMode.CEILING);
        BigDecimal expectedNetPrice = new BigDecimal("2.90").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }

    @Test
    public void testCalculateDiscountAmountLimeDiscount() {
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Item item2 = Item.builder().product(banana).quantity(1).build();
        Item item3 = Item.builder().product(lime).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, productDiscountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualGrossPrice.subtract(actualTotalDiscount));
        basket.setNetPrice(actualTotalDiscount);

        BigDecimal expectedDiscount = new BigDecimal("0.30").setScale(2, RoundingMode.CEILING);
        BigDecimal expectedNetPrice = new BigDecimal("1.65").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }

    @Test
    public void testCalculateTotalDiscountOfAmountAppleLimeMelon() {
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Item item2 = Item.builder().product(melon).quantity(7).build();
        Item item3 = Item.builder().product(lime).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, productDiscountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualGrossPrice.subtract(actualTotalDiscount));
        basket.setNetPrice(actualTotalDiscount);

        BigDecimal expectedDiscount = new BigDecimal("1.80").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal expectedNetPrice = new BigDecimal("3.45").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }


    @Test
    public void testCalculateTotalDiscountOfAmountAppleLimeMelonBeer() {
        Item item1 = Item.builder().product(apple).quantity(2).build();
        Item item2 = Item.builder().product(melon).quantity(7).build();
        Item item3 = Item.builder().product(beer).quantity(7).build();
        List<Item> items = Arrays.asList(item1, item2, item3);
        Basket basket = Basket.builder().basketItems(items).build();

        BigDecimal actualTotalDiscount = shopping.calculateTotalDiscount(basket, productDiscountList);
        BigDecimal actualGrossPrice = shopping.calculateGrossPrice(basket);

        basket.setGrossPrice(actualGrossPrice);
        basket.setTotalDiscount(actualGrossPrice.subtract(actualTotalDiscount));
        basket.setNetPrice(actualTotalDiscount);

        BigDecimal expectedDiscount = new BigDecimal("3.50").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal expectedNetPrice = new BigDecimal("14.70").setScale(2, RoundingMode.HALF_EVEN);

        assertEquals(expectedDiscount, basket.getTotalDiscount());
        assertEquals(expectedNetPrice, basket.getNetPrice());
    }


}