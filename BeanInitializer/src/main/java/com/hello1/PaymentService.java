package com.hello1;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class PaymentService {

    OrderService order;
    public PaymentService(OrderService order) {

        this.order = order;
        System.out.println("PaymentService constructor called");
    }

    public void pay(){
        System.out.println("pay");
        order.getOrderDetails();
    }

}
