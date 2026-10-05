package com.piyus.notification;

public class WhatsAppNotification implements NotificationService{
    @Override
    public void send(){
        System.out.println("WhatsApp Notification");
    }
}
