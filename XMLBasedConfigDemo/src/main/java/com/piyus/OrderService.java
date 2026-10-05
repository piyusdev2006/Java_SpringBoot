package com.piyus;

import com.piyus.notification.NotificationService;
import org.springframework.stereotype.Component;


public class OrderService {

//    private PaymentService paymentService;
    private NotificationService notification;

//    Dependency Injection Using constructor injection
//    public OrderService(NotificationService paymentService) {
//        this.paymentService = paymentService;
//    }

    public OrderService(NotificationService notification){
        this.notification = notification;
    }

    public void placeOrder(){
        System.out.println("Order Placed + retry ");
//        paymentService.pay();
        notification.send();
    }

    //    Dependency Injection Using setter injection
//    public void setPaymentService(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
}
