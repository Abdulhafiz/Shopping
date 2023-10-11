package com.abdulhafiz.shopping;

import com.abdulhafiz.shopping.basket.Item;
import com.abdulhafiz.shopping.basket.Product;
import com.abdulhafiz.shopping.discount.Buy3BeerFor5PoundDiscount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class Buy3BeerFor5PoundDiscountTest {

    Buy3BeerFor5PoundDiscount discount = Buy3BeerFor5PoundDiscount.builder().discountCode("BUY3BEERFOR5POUND").discountPrice(5.00).discountQuantity(3).build();

    @Test
    void getDiscountAmountForTotalBeers1() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(1).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(2.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    void getDiscountAmountForTotalBeers2() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(2).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(4.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    void getDiscountAmountForTotalBeers3() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(3).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(5.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    void getDiscountAmountForTotalBeers4() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(4).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(7.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    void getDiscountAmountForTotalBeers5() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(5).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(9.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }

    @Test
    void getDiscountAmountForTotalBeers6() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(6).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(10.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }
    @Test
    void getDiscountAmountForTotalBeers7() {
        Product beer = Product.builder().id("beer").name("Beer").price(2.00).build();
        Item beerItem = Item.builder().product(beer).quantity(7).build();

        BigDecimal actualDiscountAmount = discount.getDiscountAmount(beerItem);
        BigDecimal expectedDiscountAmount = BigDecimal.valueOf(12.00).setScale(2, RoundingMode.CEILING);

        assertEquals(actualDiscountAmount, expectedDiscountAmount);

    }
}