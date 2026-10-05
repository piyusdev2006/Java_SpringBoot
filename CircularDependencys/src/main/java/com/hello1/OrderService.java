package com.hello1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    private PaymentService paymentService;


//    public OrderService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }

    public void  placeOrder() {

        // payment karne ke baad
        paymentService.pay();

        // yahi orderDetails call kr lete hai
        getOrderDetails();

        System.out.println("Order placed");
    }

    public void getOrderDetails() {
        System.out.println("getOrderDetails");
    }
}

