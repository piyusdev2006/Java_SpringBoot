package com.hello1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    PaymentService payment;

    @Autowired
    public OrderService(PaymentService Payment) {
        this.payment = Payment;
    }

    public void placeOrder() {
        System.out.println("Order Placed");

        payment.pay();
    }
}
