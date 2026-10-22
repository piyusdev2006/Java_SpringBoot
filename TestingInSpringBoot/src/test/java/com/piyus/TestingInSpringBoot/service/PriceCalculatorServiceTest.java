package com.piyus.TestingInSpringBoot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceCalculatorServiceTest {

    // first take reference of the file we need to test
    // inject dependency directly - springboot na handle kre

    private PriceCalculatorService priceCalculator;

    @BeforeEach
    void setup() {
        priceCalculator = new PriceCalculatorService();
    }

    //@AfterEach


    @Test
    void isDisCountAppliedToPrice(){

        //arrange --> Initial Input
        double price = 1000.0;
        double discount = 20.0;

        //Act --> Action
        double actualPrice
                = priceCalculator.calculatePrice(price, discount);

        //ExpectedResult --> Assertion : assertEquals()
        // - it tells wheater two values are equal are not
        //assertEquals(800.0, actualPrice, 0.001);

        //assertTrue();
        //assertFalse();
        //assertNull();
        //assertNotNull();

        // Exceptions : assertThrows()
    }


    @Test
    void shouldRejectDisCountAbove100(){
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class,
                () -> priceCalculator
                    .calculatePrice(1000, 120)
        );

        assertEquals(
                "Discount must be between 0 and 100",
                exception.getMessage()
        );
    }

}
