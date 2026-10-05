package com.piyus;

import com.piyus.notification.EmailNotificationService;
import com.piyus.notification.NotificationService;
import com.piyus.notification.SMSNotificationService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main() {

        NotificationService notification = new SMSNotificationService();

        // injecting dependency through Main class

        // OrderService order = new OrderService(notification);
        // order.placeOrder();

        OrderService order1 = new OrderService();
        // injecting dependency through setter
        order1.setNotification(notification);
        order1.placeOrder();

    }
}
