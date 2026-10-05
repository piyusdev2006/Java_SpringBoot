package com.piyus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");

        // here we get bean by type
//        OrderService order = context.getBean(OrderService.class);
//        OrderService order2 = context.getBean(OrderService.class);// aisa nhi kar sakte


        // here we get bean by id/name
//        OrderService order3 = (OrderService) context.getBean("orderService");
//        OrderService order2 = (OrderService) context.getBean("orderService2");

        // tisra tarika ye hai best way to get bean
        OrderService order = context.getBean("orderService", OrderService.class);

//        NotificationService payment = context.getBean("paymentBean", NotificationService.class);
//        payment.pay();


        order.placeOrder();

        UserService user = context.getBean("userService", UserService.class);
        System.out.println( user.getUsers());

        context.close();
    }

}
