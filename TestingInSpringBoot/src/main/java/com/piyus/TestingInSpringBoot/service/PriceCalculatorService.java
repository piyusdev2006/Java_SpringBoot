package com.piyus.TestingInSpringBoot.service;

import com.piyus.TestingInSpringBoot.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService {

    // discount % calculator
    public double calculatePrice(
            double price, double discount) {

        if(price < 0) {
            throw new IllegalArgumentException(
                    "Price must be greater than zero"
            );
        }

        if(discount < 0 || discount > 100) {
            throw new IllegalArgumentException(
                    "Discount must be between 0 and 100"
            );
        }

        return price - (price * discount / 100);
    }
}
