package com.hello.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("card")
public class CardPayment implements PaymentService{
    @Override
    public void pay() {
        System.out.println("Paying via Card");
    }
}
