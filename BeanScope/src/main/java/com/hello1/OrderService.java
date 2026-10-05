package com.hello1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
//@Scope("singleton")
public class OrderService {

    public OrderService(){
        System.out.println("Order service created");
    }

    public void placeOrder(){
        System.out.println("order Placed");
    }
}
