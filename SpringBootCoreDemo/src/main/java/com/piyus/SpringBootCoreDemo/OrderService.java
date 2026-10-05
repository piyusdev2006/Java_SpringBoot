package com.piyus.SpringBootCoreDemo;

import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private PaymentService payment;

    public OrderService(PaymentService payment){
        this.payment = payment;
    }

    public void placeOrder(){

        payment.pay();
        System.out.println("Order Placed Successfully");
    }
}
