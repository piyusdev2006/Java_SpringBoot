package com.hello;

import org.example.CartService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    static void main() {

        /*
            below line ka matlab hai ek IOC container up karo
            using Annotation based configuration aur configuration
            rules tumhein milenge AppConfig.class se
        */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // hum yaha reflection API use kr rhe hai
        OrderService order = context.getBean(OrderService.class);
        order.placeOrder();

        User user = context.getBean(User.class);
        System.out.println(user.getName());

        CartService cs = context.getBean(CartService.class);
        cs.addToCart();
    }
}


