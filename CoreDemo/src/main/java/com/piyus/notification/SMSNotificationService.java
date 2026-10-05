package com.piyus.notification;

public class SMSNotificationService implements NotificationService{
    @Override
    public void sendNotification() {
        System.out.println("SMS notification sent");
    }
}
