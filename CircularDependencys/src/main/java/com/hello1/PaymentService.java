package com.hello1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {



//    public PaymentService(OrderService orderService) {
//        this.orderService = orderService;
//    }

    public void pay() {
        System.out.println("payment done");

        // ye kaam Payement service ka hona hi nhi chahiye
        //  orderService.getOrderDetails();
    }
}
