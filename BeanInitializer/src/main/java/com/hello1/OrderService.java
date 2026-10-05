package com.hello1;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Lazy
public class OrderService {

    PaymentService payment;

    // Payment service ka Object bhi mat banana kyoki @Lazy laga hua hai,
    // aur payment service ki dependency ko bhi inject mat krna kyoki
    // yaha bhi @Lazy laga hua hai
//    public OrderService(@Lazy PaymentService payment){


    public OrderService(@Lazy PaymentService payment) {
        this.payment = payment;
        System.out.println("Order created");

    }


    public void placeOrder(){
        payment.pay();

        System.out.println("Order placed");
    }

    public void getOrderDetails(){
        System.out.println("Order Details");
    }
}


