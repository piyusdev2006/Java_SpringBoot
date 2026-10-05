package com.hello1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    static void main() {
       // ApplicationContext context
        ConfigurableApplicationContext context
                =
                new AnnotationConfigApplicationContext(Config.class);

          // OrderService order = context.getBean(OrderService.class);
          // order.placeOrder();

        Config con = context.getBean(Config.class);
        con.demo();

        CartService cart = context.getBean(CartService.class);
        System.out.println(cart.getValue(2));


        // Manually Destroying Bean
        context.close();
        // ApplicationContext ke pass close method nhi hai,
        // wo hai iske child interface ConfigurableApplictionContext ke pass

        // But mere yha close karne se pahle spring ka destroy callback remove kar dega
    }
}
