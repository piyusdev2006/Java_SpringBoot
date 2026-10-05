package com.piyus;

import com.piyus.notification.EmailNotificationService;
import com.piyus.notification.NotificationService;
import com.piyus.notification.PopUpNotificationService;

public class OrderService {

    // yaha interface as variable type apr andar se concretely object hi bna rha hu
    // tightly coupled hai
    // yaha do SOLID principle break ho rhe hai :-woh hai SRP and OCP

    NotificationService notification;

    // dependency injection through constructors
    public OrderService(NotificationService notify){
        notification = notify;
    }

    public OrderService(){};

    public void placeOrder(){
        System.out.println("order placed");
        notification.sendNotification();
    }

    // dependency injection through setter
    public void setNotification(NotificationService notification) {
        this.notification = notification;
    }
}
