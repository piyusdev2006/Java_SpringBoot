package com.hello1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.ObjectInputFilter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ApplicationContext context
                =
                new AnnotationConfigApplicationContext(Config.class);

        OrderService order = context.getBean(OrderService.class);
//        PaymentService payment = context.getBean(PaymentService.class);
        System.out.println("Payment Service not started yet");
        order.placeOrder();
    } 
}
